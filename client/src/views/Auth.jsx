import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import AuthWindow from '../components/AuthWindow.jsx'
import axios from 'axios';

const Auth = props => {
  const [user, setUser] = useState('');
  const [password, setPassword] = useState('');
  const [authenticated, setAuthenticated] = useState(false);
  const [toggle, setToggle] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    if (localStorage.getItem('token')) navigate('/');
  }, [authenticated])


  const loginHandler = (e) => {
    e.preventDefault();
    axios({
      method: 'POST',
      url: 'https://ejercicio-practico.onrender.com/login/create',
      data: { name: user, password },
    }).then(res => {
      if (res.data !== 'Error') {
        localStorage.setItem('token', res.data);
        setAuthenticated(true);
      }
      else
        alert('Usuario o contraseña invalido');
    }).catch(e => alert(e))
  }

  const registerHandler = (e) => {
    e.preventDefault();
    axios({
      method: 'POST',
      url: 'https://ejercicio-practico.onrender.com/registration/',
      data: { name: user, password }
    }).then(token => {
      setToggle(!toggle);
      alert('Usuario Creado')
    }).catch(e => alert(e))
  }

  return (
    <section className='w-full h-screen flex justify-center items-center'>
      <div className='h-fit p-6 shadow-md bg-slate-50 border border-blue-700'>
        <button
          onClick={() => setToggle(!toggle)}
          className='w-full p-2 bg-slate-700 mb-5 text-white'
          type="button"
        >{toggle ? "Regitrar" : "Login"}</button>
        {toggle
          && <AuthWindow header='LOGIN' buttonText='INGRESAR' handler={loginHandler} user={user} password={password} setUser={setUser} setPassword={setPassword} />
          || <AuthWindow header='REGISTRO' buttonText='CREAR' handler={registerHandler} user={user} password={password} setUser={setUser} setPassword={setPassword} />}
      </div>
    </section>
  )
}

export default Auth;
