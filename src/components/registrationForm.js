import React, { useState } from 'react';
import './style.css';
import { database } from '../firebase';
import { ref, push, child, update } from 'firebase/database';

function RegistrationForm() {
  const [appNum, setAppNum] = useState(null);
  const [appNumFake, setAppNumFake] = useState(null);
  const [appCode, setAppCode] = useState(null);
  const [appYear, setAppYear] = useState(null);
  const [email, setEmail] = useState(null);

  const handleInputChange = (e) => {
    const { id, value } = e.target;
    if (id === 'appNum') {
      setAppNum(value);
    }
    if (id === 'apppNumFake') {
      setAppNumFake(value);
    }
    if (id === 'appCode') {
      setAppCode(value);
    }
    if (id === 'appYear') {
      setAppYear(value);
    }
    if (id === 'email') {
      setEmail(value);
    }
  };

  const handleSubmit = () => {
    let obj = {
      appNum: appNum,
      appNumFake: appNumFake,
      appCode: appCode,
      appYear: appYear,
      email: email,
    };
    const newPostKey = push(child(ref(database), 'posts')).key;
    const updates = {};
    updates['/' + newPostKey] = obj;

    return update(ref(database), updates);
  };

  return (
    <div className='form'>
      <div className='form-body'>
        <div className='form-row'>
          <label className='form__label'>Application number </label>
          <input
            type='text'
            id='appNum'
            className='form__input'
            value={appNum}
            onChange={(e) => handleInputChange(e)}
            placeholder='12345*'
          />
        </div>
        <div className='form-row'>
          <label className='form__label'>-XX </label>
          <input
            type='text'
            id='appNumFake'
            className='form__input'
            value={appNumFake}
            onChange={(e) => handleInputChange(e)}
            placeholder='-XX'
          />
        </div>
        <div className='form-row'>
          <label className='form__label'>CC* </label>
          <input
            type='text'
            id='appCode'
            className='form__input'
            value={appCode}
            onChange={(e) => handleInputChange(e)}
            placeholder='Typ řízení (СС)*'
          />
        </div>
        <div className='form-row'>
          <label className='form__label'>YYYY* </label>
          <input
            type='text'
            id='appYear'
            className='form__input'
            value={appYear}
            onChange={(e) => handleInputChange(e)}
            placeholder='Rok'
          />
        </div>
        <div className='form-row'>
          <label className='form__label'>Email </label>
          <input
            type='email'
            id='email'
            className='form__input'
            value={email}
            onChange={(e) => handleInputChange(e)}
            placeholder='example@email.com'
          />
        </div>
      </div>
      <div class='footer'>
        <button type='submit' className='btn' onClick={() => handleSubmit()}>
          Register
        </button>
      </div>
    </div>
  );
}

export default RegistrationForm;