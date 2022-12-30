import { PropTypes } from 'prop-types'
import { useEffect, useState } from 'react'

const Sells = props => {
  const [ventas, setVentas] = useState(null)

  useEffect(() => {
    fetch('http://localhost:8080/venta/getall')
      .then(res => res.json())
      .then(json => {
        setVentas(json);
      })
  }, [])

  const formHandler = (e) => {
    e.preventDefault();
    fetch('http://localhost:8080/venta/get', {
      method: 'POST',
      headers: {
        'Authorization': localStorage.token,
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ folio: '' })
    }).then(res => res.json())
      .then(venta => {
        localStorage.token = token;
      })
  }

  return (
    <section className='w-full h-screen p-10'>
      <h1 className='text-4xl mb-5'>Consulta de Ventas</h1>
      <form className='flex gap-5 mb-5' onSubmit={formHandler}>
        <div>
          <label htmlFor="folio">Folio de Venta:</label><br />
          <input className='p-1' type="number" name="folio" id='folio' placeholder='XXXX' />
        </div>
        <button className='self-end p-1 border border-slate-500' type="submit">Consultar</button>
      </form>
      <h2 className='text-3xl mb-5'>Listado de Productos</h2>
      <table className='border w-full'>
        <thead>
          <tr>
            <th>No.</th>
            <th>Producto</th>
            <th>Cantidad</th>
            <th>Precio Unitario</th>
            <th>Total</th>
          </tr>
        </thead>
        <tbody>
          {ventas?.length > 0 && ventas.map(venta => (
            <tr>
              <td>{venta.id}</td>
              <td>{venta.description}</td>
              <td>{venta.quantitySold}</td>
              <td>{venta.unitaryPrice}</td>
              <td>{venta.total}</td>
            </tr>
          )) || <tr className='w-full'><td colSpan={5} className='text-center w-full'>No hay ventas</td></tr>}
        </tbody>
      </table>
    </section>
  )
}

Sells.propTypes = {

}

export default Sells
