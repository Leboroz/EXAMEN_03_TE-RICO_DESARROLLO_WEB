import { PropTypes } from 'prop-types';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import Modal from '../components/Modal';
import FormField from '../components/FormField';
import { data } from 'autoprefixer';

const Sells = () => {
  const [ventas, setVentas] = useState([]);
  const [filtered, setFiltered] = useState([]);
  const [folioFilter, setFolioFilter] = useState('');
  const [show, setShow] = useState(false);
  const [description, setDescription] = useState('');
  const [id, setId] = useState({ i: -1, id: -1 });
  const [quantitySold, setQuantitySold] = useState('');
  const [unitaryPrice, setUnitaryPrice] = useState('');
  const [folio, setFolio] = useState('');
  const navigation = useNavigate();

  const clearFields = () => {
    setFolio('');
    setDescription('');
    setQuantitySold('');
    setUnitaryPrice('');
  }

  useEffect(() => {
    if (!localStorage.getItem('token')) {
      navigation('/auth');
      return;
    }

    (async () => {
      const response = axios({
        method: 'GET',
        url: 'https://ejercicio-practico.onrender.com/venta/getall',
        headers: {
          Authorization: localStorage.getItem('token'),
        }
      })
        .then(res => setVentas(res.data))
        .catch(e => alert(e));
    })()
  }, []);

  const formHandler = (e) => {
    e.preventDefault();
    const arr = ventas.filter(venta => venta.folio === parseInt(folioFilter, 10))
    setFiltered(arr);
    if (!arr.length) alert('El folio ingresado no concuerda con la data');
    setFolioFilter('');
  }

  const createHandler = async (e) => {
    e.preventDefault();
    try {
      const res = await axios({
        method: 'POST',
        url: 'https://ejercicio-practico.onrender.com/venta/add',
        headers: {
          Authorization: localStorage.getItem('token'),
        },
        data: { description, quantitySold, unitaryPrice, folio }
      })
      setVentas(prev => {
        prev.unshift(res.data);
        return prev;
      })
    } catch (error) {
      alert(error)
    }
    setShow(false);
    clearFields();
  }

  const updateHandler = (e) => {
    e.preventDefault();

    const res = axios({
      method: 'POST',
      url: `https://ejercicio-practico.onrender.com/venta/edit/${id.id}`,
      data: { description, quantitySold, unitaryPrice, folio }
    }).then(res => {
      setVentas(prev => {
        prev[id.i] = {
          description,
          quantitySold,
          unitaryPrice,
          folio
        };
        return [...prev];
      })
      setId({ i: -1, id: -1 })
    }).catch(e => {
      alert(e)
      setId({ i: -1, id: -1 })
    })

    setShow(false);
    clearFields();
  }

  return (
    <section className='w-full h-screen p-10'>
      {show
        && <Modal handler={id.id < 0 ? createHandler : updateHandler} closeHandler={() => {
          setShow(false);
          clearFields();
        }}>
          <h1 className='text-2xl'>Crea una venta Venta</h1>
          <FormField type='text' name='description' label='Producto: ' value={description} onChange={e => setDescription(e.target.value)} />
          <FormField type='text' name='quantitySold' label='Cantidad: ' value={quantitySold} onChange={e => setQuantitySold(e.target.value)} />
          <FormField type='text' name='unitaryPrice' label='Precio Unitario: ' value={unitaryPrice} onChange={e => setUnitaryPrice(e.target.value)} />
          <FormField type='text' name='folio' label='Folio: ' value={folio} onChange={e => setFolio(e.target.value)} />
          <button type="submit" >Crear</button>
        </Modal>
      }
      <div className='w-full lg:w-1/2'>
        <h1 className='text-4xl mb-5'>Consulta de Ventas</h1>
        <button onClick={() => setShow(true)} className='ml-auto border border-black float-right rounded-full p-3 leading-none text-xl hover:bg-black hover:text-white' type="button">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" className="w-6 h-6">
            <path strokeLinecap="round" strokeLinejoin="round" d="M12 6v12m6-6H6" />
          </svg>
        </button>
      </div>
      <form className='flex gap-5 mb-5' onSubmit={formHandler}>
        <div>
          <label htmlFor="folio">Folio de Venta:</label><br />
          <input
            className='p-1 border border-slate-500'
            type="number"
            name="folio"
            value={folioFilter}
            onChange={(e) => setFolioFilter(e.target.value)}
            id='folio'
            placeholder='XXXX'
          />
        </div>
        <button className='self-end p-1 border border-slate-500 hover:bg-black hover:text-white' type="submit">Consultar</button>
      </form>
      {filtered.length > 0 && <h2 className='text-3xl mb-5'>Listado de Productos</h2>
        && <table className='border w-full'>
          <thead>
            <tr>
              <th>No.</th>
              <th>Producto</th>
              <th>Cantidad</th>
              <th>Precio Unitario</th>
              <th>Total</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {filtered.map((venta, i) => (
              <tr key={venta.id}>
                <td>{i + 1}</td>
                <td>{venta.description}</td>
                <td>{venta.quantitySold}</td>
                <td>{venta.unitaryPrice}</td>
                <td>{venta.quantitySold * venta.unitaryPrice}</td>
                <td>
                  <button
                    className='border border-red-500 text-red-500 hover:bg-slate-500 hover:text-white p-2'
                    onClick={() => {
                      const confirmation = confirm('¿Estas seguro?')
                      if (confirmation) {
                        axios({
                          method: 'DELETE',
                          url: `https://ejercicio-practico.onrender.com/venta/delete/${venta.id}`,
                        }).then(() => {
                          setVentas(arr => {
                            arr.splice(i, 1);
                            return [...arr];
                          })
                        }).catch(e => alert(e));
                      }
                    }}
                    type='button'
                  >Borrar</button>

                  <button
                    className='border border-slate-500 text-slate-500 hover:bg-slate-500 hover:text-white float-right p-2'
                    onClick={() => {
                      setDescription(venta.description);
                      setQuantitySold(venta.quantitySold);
                      setUnitaryPrice(venta.unitaryPrice);
                      setFolio(venta.folio);
                      setShow(true);
                      setId({ i, id: venta.id })
                    }}
                    type='button'
                  >Actualizar</button>

                </td>
              </tr>
            )) || <tr className='w-full'><td colSpan={6} className='text-center w-full'>No hay ventas</td></tr>}
          </tbody>
        </table>}
      {filtered.length > 0 && <div className='flex w-full justify-around'>
        <div className='flex'>
          <p>Total Productos: </p>
          <span className='border-b-2 w-24 text-center'>{filtered.reduce((acu, curr) => acu + curr.quantitySold, 0)}</span>
        </div>
        <div className='flex'>
          <p>Total Venta: </p>
          <span className='border-b w-24 text-center'>{filtered.reduce((acu, curr) => curr.quantitySold * curr.unitaryPrice + acu, 0)}</span>
        </div>
      </div>}
    </section>
  )
}

Sells.propTypes = {

}

export default Sells
