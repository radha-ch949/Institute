import React, { useState } from 'react';
import { Snackbar } from '@mui/material';
import MuiAlert from '@mui/material/Alert';
import RegisterForm from './RegisterForm';
import UserDashboard from './UserDashboard';
import AdminDashboard from './AdminDashboard';
import LoginForm from './LoginForm';

const App = () => {
  const [loggedInUser, setLoggedInUser] = useState(null);
  const [showRegisterForm, setShowRegisterForm] = useState(false);
  const [alertOpen, setAlertOpen] = useState(false);
  const [alertMessage, setAlertMessage] = useState('');
  const [alertSeverity, setAlertSeverity] = useState('success');

  const handleRegisterSuccess = () => {
    setAlertSeverity('success');
    setAlertMessage('Registered successfully!');
    setAlertOpen(true);
    // Optionally, you can add other logic here if needed
  };

  const handleBackToLogin = () => {
    setShowRegisterForm(false);
    // Optionally, you can add other logic here if needed
  };

  const handleAlertClose = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }
    setAlertOpen(false);
  };

  return (
    <div className="container">
      {loggedInUser ? (
        <>
          {loggedInUser.userType === 'admin' ? (
            <AdminDashboard onBackToLogin={() => setLoggedInUser(null)} loggedInUser={loggedInUser} />
          ) : loggedInUser.userType === 'user' ? (
            <UserDashboard onBackToLogin={() => setLoggedInUser(null)} loggedInUser={loggedInUser} />
          ) : (
            <p>Invalid userType: {loggedInUser.userType}</p>
          )}
        </>
      ) : (
        <>
          {showRegisterForm ? (
            <>
              <RegisterForm
                onRegister={handleRegisterSuccess}
                onToggleForm={() => setShowRegisterForm(false)}
                // onRegisteredSuccessfully={() => setShowRegisterForm(false)}
              />
            </>
          ) : (
            <>
              <LoginForm
                onLogin={(email, userType) => setLoggedInUser({ email, userType })}
                onToggleForm={() => setShowRegisterForm(true)}
              />
            </>
          )}
        </>
      )}
      <Snackbar open={alertOpen} autoHideDuration={4000} onClose={handleAlertClose}>
        <MuiAlert elevation={6} variant="filled" onClose={handleAlertClose} severity={alertSeverity}>
          {alertMessage}
        </MuiAlert>
      </Snackbar>
    </div>
  );
};

export default App;
