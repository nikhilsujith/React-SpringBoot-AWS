import React, { useState, useEffect } from 'react';
import logo from './logo.svg';
import './App.css';
import axios from "axios";

//User Profiles class
const UserProfiles = () => {
  /*
  *the below line is used to set the state.
  * The line is created after creating  "const data = response.data"
  * */
  const [userProfiles, setUserProfiles] = useState([]);

  const fetchUserProfiles = () => {
    axios.get("http://localhost:8080/api/v1/user-profile").then(response => {
      console.log(response);
      //store data being sent from backed into data and set the state
      setUserProfiles(response.data);
    });
  };

  useEffect( () => {
    fetchUserProfiles();
  }, []);

  return userProfiles.map((userProfile, index) => {
    return (
        <div key={index}>
            <h1>{ userProfile.username }</h1>
          <p>{ userProfile.userProfileID }</p>
        </div>
      )
  } )
}
function App() {
  return (
      <div className="App">
        <UserProfiles />
      </div>
  )
}

export default App;
