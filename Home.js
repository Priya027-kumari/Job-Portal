import React, {useEffect, useState} from 'react';
import API from '../api';
export default function Home(){
  const [listings,setListings] = useState([]);
  useEffect(()=>{ API.get('/career/listings').then(r=>setListings(r.data)); },[]);
  return (
    <div>
      <h3>Open Positions</h3>
      <div className="row">
        {listings.map(l=>(
          <div className="col-md-6" key={l.id}>
            <div className="card mb-3">
              <div className="card-body">
                <h5>{l.title}</h5>
                <p>{l.description}</p>
                <p><strong>Skills:</strong> {l.skills}</p>
                <a className="btn btn-primary" href={'/listings/'+l.id}>View</a>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
