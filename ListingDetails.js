import React, {useEffect, useState} from 'react';
import { useParams } from 'react-router-dom';
import API from '../api';
export default function ListingDetails(){
  const {id} = useParams(); const [listing,setListing]=useState(null); const [cover,setCover]=useState(''); const [msg,setMsg]=useState('');
  useEffect(()=>{ API.get('/career/listings').then(r=>{ const found = r.data.find(x=>String(x.id)===String(id)); setListing(found); }); },[id]);
  const apply = async e=>{ e.preventDefault(); try{ await API.post(`/career/listings/${id}/apply`,{coverNote:cover}); setMsg('Applied'); }catch(err){ setMsg('Apply failed'); } };
  if(!listing) return <div>Loading...</div>;
  return (
    <div className="row"><div className="col-md-8">
      <h3>{listing.title}</h3><p>{listing.description}</p>
      <form onSubmit={apply}><div className="mb-3"><textarea className="form-control" value={cover} onChange={e=>setCover(e.target.value)} placeholder="Cover note"/></div><button className="btn btn-primary" type="submit">Apply</button></form>
      <p>{msg}</p>
    </div></div>
  );
}
