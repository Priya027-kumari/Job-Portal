import React, {useState} from 'react';
import API from '../api';
export default function PostListing(){
  const [title,setTitle]=useState(''); const [desc,setDesc]=useState(''); const [skills,setSkills]=useState(''); const [msg,setMsg]=useState('');
  const submit = async e=>{ e.preventDefault(); try{ await API.post('/career/listings',{title,description:desc,skills,location:'',salary:0}); setMsg('Posted'); }catch(err){ setMsg('Failed'); } };
  return (
    <div className="row justify-content-center"><div className="col-md-8">
      <h3>Post Listing</h3>
      <form onSubmit={submit}>
        <div className="mb-3"><input className="form-control" value={title} onChange={e=>setTitle(e.target.value)} placeholder="Title" required/></div>
        <div className="mb-3"><textarea className="form-control" value={desc} onChange={e=>setDesc(e.target.value)} placeholder="Description" required/></div>
        <div className="mb-3"><input className="form-control" value={skills} onChange={e=>setSkills(e.target.value)} placeholder="Skills"/></div>
        <button className="btn btn-primary" type="submit">Post</button>
      </form>
      <p>{msg}</p>
    </div></div>
  );
}
