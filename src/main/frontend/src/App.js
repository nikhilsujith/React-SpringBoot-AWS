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
                {userProfile.userProfileID ? (
                    <img
                        src = { `http://localhost:8080/api/v1/user-profile/${userProfile.userProfileID}/image/download` }
                    />
                ) : null}
                <br />
                <br />
                <h1>{ userProfile.username }</h1>
                <p>{ userProfile.userProfileID }</p>
                <Dropzone userProfileID = {userProfile.userProfileID}/>
                {/*the above line can be written as"
                { ...userProfile }*/}
                <br />
            </div>
        );
    } );
};


/*Using React Drop zone to drag and drop images*/
function Dropzone({ userProfileID }) {
    const onDrop = useCallback(acceptedFiles => {
        // Do something with the files
        const file = acceptedFiles[0];
        console.log(file);

        /* Sending image to API */

        const formData = new FormData();
        //the name "file" should be same as in UserProfileController.java
        formData.append("file", file);

        axios.post(
            `http://localhost:8080/api/v1/user-profile/${userProfileID}/image/upload`,
            formData,
            {
                headers: {
                    "Content-Type": "multipart/form-data"
                }
            }
        ).then ( () => {
            console.log("File uploaded successfully");
        } ).catch(err => {
            console.log(err);
        });

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