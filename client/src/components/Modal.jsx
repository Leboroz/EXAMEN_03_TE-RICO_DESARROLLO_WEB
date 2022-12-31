import { PropTypes } from 'prop-types'
import { useState } from 'react'
import Form from './Form'
const Modal = props => {
  return (
    <div className='flex justify-center items-center w-screen h-screen fixed top-0 left-0'>
      <div className='w-full h-full absolute top-0 left-0 border-gray-300 blur bg-gray-300 bg-opacity-70' />
      <div className='bg-white z-10 relative p-6'>
        <button className='text-2xl absolute top-1 right-3' onClick={() => props.closeHandler(false)} type="button">x</button>
        <Form onSubmit={props.handler}>
          {props.children}
        </Form>
      </div>
    </div>
  )
}

Modal.propTypes = {

}

export default Modal
