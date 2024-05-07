import React, { useContext, useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import Chat from "../chat/Chat";
import axios from "axios";
import "./Chat.scss"
import Navbar from './../Navbar'

function ChatPage({items, cart , setCart, setData}) {
  const { userName, sender } = useParams();
  const [profilePic, setProfilePic] = useState("");

  useEffect(() => {
    const fetchProfilePhoto = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/users/profile-photo/${userName}`
        );
        setProfilePic(response.data);
      } catch (error) {
        console.error("Error fetching profile photo:", error);
      }
    };

    fetchProfilePhoto();
  }, [userName]);

  const Layout = () => {
    return (
      <div>
        <Navbar cart={cart} setData={setData} userName={userName}/>
        <div style={{ display: "flex" }}>
          <div style={{ flex: 6 }}>
            <div className="chat">
               <Chat userName={userName} sender={sender} profilePic={profilePic}/>
            </div>
          </div>
        </div>
      </div>
    );
  };

  return <Layout />;
}

export default ChatPage;
