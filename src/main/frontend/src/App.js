import React, { useState, useEffect, useCallback } from 'react';
import logo from './logo.svg';
import './App.css';
import axios from "axios";
import {useDropzone} from 'react-dropzone'

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
            {/*Profile Image*/}
            <br />
            <br />
            <h1>{ userProfile.username }</h1>
            <p>{ userProfile.userProfileID }</p>
            <Dropzone />
            <br />
        </div>
      );
  } );
};


/*Using React Drop zone to drag and drop images*/
function Dropzone() {
    const onDrop = useCallback(acceptedFiles => {
        // Do something with the files
        const file = acceptedFiles[0];
        console.log(file);
    }, [])
    const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

    return (
        <div {...getRootProps()}>
            <input {...getInputProps()} />
            {
                isDragActive ?
                    <p>Drop the files here ...</p> :
                    <p>Drag and Drop an image / Click to select</p>
            }
        </div>
    )
}



function App() {
  return (
      <div className="App">
        <UserProfiles />
      </div>
  )
}

export default App;
