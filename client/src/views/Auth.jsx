import { useEffect, useState } from 'react'
import Form from '../components/Form.jsx'
import FormField from '../components/FormField.jsx'
import Header from '../components/Header.jsx'

const Auth = () => {
  const [user, setUser] = useState('');
  const [password, setPassword] = useState('');
  useEffect(() => {
  }, [])

  const formHandler = (e) => {
    e.preventDefault();
    fetch('http://localhost:8080/login/create', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ email: user, password })
    }).then(res => res.text())
      .then(token => {
        localStorage.token = token;
      })
  }

  return (
    <section className='w-full h-screen flex justify-center items-center'>
      <div className='h-fit p-6 shadow-md bg-slate-50 border border-blue-700'>
        <Header text="LOGIN" />
        <Form onSubmit={formHandler}>
          <FormField type='text' name="user" label="Usuario:" value={user} onChange={e => setUser(e.target.value)} />
          <FormField type='password' name="password" label="Contraseña:" value={password} onChange={e => setPassword(e.target.value)} />
          <input className='p-2 w-fit self-center border border-blue-700' type="submit" value="INGRESAR" />
        </Form>
      </div>
    </section>
  )
}

export default Auth;
