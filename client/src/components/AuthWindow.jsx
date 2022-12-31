import Form from '../components/Form.jsx'
import FormField from '../components/FormField.jsx'
import Header from '../components/Header.jsx'

const Auth = props => {

  return (
    <div>
      <Header text={props.header} />
      <Form onSubmit={props.handler}>
        <FormField type='text' name="user" label="Usuario:" value={props.user} onChange={e => props.setUser(e.target.value)} />
        <FormField type='password' name="password" label="Contraseña:" value={props.password} onChange={e => props.setPassword(e.target.value)} />
        <input className='p-2 w-fit self-center border border-blue-700' type="submit" value={props.buttonText} />
      </Form>
    </div>
  )
}

export default Auth;
