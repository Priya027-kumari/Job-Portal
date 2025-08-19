import React, {useState} from 'react';
import API, { setAuthToken } from '../api';

export default function Login(){
  const [username,setUsername]=useState(''); const [password,setPassword]=useState(''); const [msg,setMsg]=useState('');
  const submit = async (e)=>{ e.preventDefault(); try{ const r = await API.post('/auth/login',{username,password}); const token = r.data.token; localStorage.setItem('cc_token',token); setAuthToken(token); setMsg('Logged in'); }catch(err){ setMsg('Login failed'); } };
  return (
    <div className="row justify-content-center"><div className="col-md-6">
      <h3>Login</h3>
      <form onSubmit={submit}>
        <div className="mb-3"><input className="form-control" value={username} onChange={e=>setUsername(e.target.value)} placeholder="username" required/></div>
        <div className="mb-3"><input className="form-control" type="password" value={password} onChange={e=>setPassword(e.target.value)} placeholder="password" required/></div>
        <button className="btn btn-primary" type="submit">Login</button>
      </form>
      <p>{msg}</p>
    </div></div>
  );
}
