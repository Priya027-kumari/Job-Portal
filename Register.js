import React, {useState} from 'react';
import API from '../api';
export default function Register(){
  const [username,setUsername]=useState(''); const [password,setPassword]=useState(''); const [role,setRole]=useState('ROLE_SEEKER'); const [msg,setMsg]=useState('');
  const submit = async e=>{ e.preventDefault(); try{ await API.post('/auth/register',{username,password,role}); setMsg('Registered, please login'); }catch(err){ setMsg('Register failed'); } };
  return (
    <div className="row justify-content-center"><div className="col-md-6">
      <h3>Register</h3>
      <form onSubmit={submit}>
        <div className="mb-3"><input className="form-control" value={username} onChange={e=>setUsername(e.target.value)} placeholder="username" required/></div>
        <div className="mb-3"><input className="form-control" type="password" value={password} onChange={e=>setPassword(e.target.value)} placeholder="password" required/></div>
        <div className="mb-3">
          <select className="form-select" value={role} onChange={e=>setRole(e.target.value)}>
            <option value="ROLE_SEEKER">Job Seeker</option>
            <option value="ROLE_EMPLOYER">Employer</option>
          </select>
        </div>
        <button className="btn btn-primary" type="submit">Register</button>
      </form>
      <p>{msg}</p>
    </div></div>
  );
}
