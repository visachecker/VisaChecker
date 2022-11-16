import { getDatabase } from 'firebase/database'
// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyCBdpiFmk7oLZsLal-zBDYkm40OO2-hw9w",
  authDomain: "mvcr-database.firebaseapp.com",
  databaseURL: "https://mvcr-database-default-rtdb.europe-west1.firebasedatabase.app",
  projectId: "mvcr-database",
  storageBucket: "mvcr-database.appspot.com",
  messagingSenderId: "389674513041",
  appId: "1:389674513041:web:7eb4d6875a7b60bbd44530"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);

export const database = getDatabase(app);