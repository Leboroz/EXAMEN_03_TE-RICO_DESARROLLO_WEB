import { PropTypes } from 'prop-types'

const FormField = props => {
  return (
    <div className='flex justify-between'>
      <label htmlFor={props.name}>{props.label}</label>
      <input
        className='rounded border border-gray-100 outline-blue-700'
        type={props.type}
        name={props.name}
        id={props.name}
        onChange={props.onChange}
        value={props.value}
        required
      />
    </div>
  )
}

FormField.propTypes = {
  name: PropTypes.string,
  label: PropTypes.string,
  type: PropTypes.string,
  onchange: PropTypes.func,
  value: PropTypes.string || PropTypes.number
}

export default FormField
