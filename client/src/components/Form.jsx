import { PropTypes } from 'prop-types'

const Form = props => {
  return (
    <form className='flex flex-col gap-3' onSubmit={props.onSubmit}>
      {props.children}
    </form>
  )
}

Form.propTypes = {
  children: PropTypes.oneOfType([
          PropTypes.arrayOf(PropTypes.node),
          PropTypes.node
      ]).isRequired,
  onSubmit: PropTypes.func
}

export default Form
