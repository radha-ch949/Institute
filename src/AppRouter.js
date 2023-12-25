
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './LoginForm';
import Register from './RegisterForm';
import AdminDashboard from './AdminDashboard';
import UserDashboard from './UserDashboard';
import MasterLogin from './MasterLogin';
import MasterRegister from './MasterRegister';

const App = () => {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<Login />} /> Route for the root URL
          <Route path="/login" element={<Login />} />
          <Route path='/masterLogin' element={<MasterLogin/>} />
          <Route path='/masterRegister' element={<MasterRegister/>} />
          <Route path="/register" element={<Register />} />
          <Route path="/admindashboard" element={<AdminDashboard />} />
          <Route path="/userdashboard" element={<UserDashboard />} />

          
        </Routes>
      </div>
    </Router>
  );
};

export default App;