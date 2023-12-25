import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import {
  Container,
  Typography,
  TextField,
  Button,
  Box,
  TableHead,
  Table,
  TableBody,
  TableRow,
  TableCell,
  Paper,
  Tabs,
  Tab,
  TableContainer,
} from '@mui/material';

const AdminDashboard = ({  onBackToLogin, loggedInUser }) => {
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [courseName, setCourseName] = useState('');
  const [courseFees, setCourseFees] = useState('');
  const [courseDuration, setCourseDuration] = useState('');
  const [batch, setBatch] = useState('');
  const [strengthOfStudents, setStrengthOfStudents] = useState('');
  const [courses, setCourses] = useState([]);
  const [selectedTab, setSelectedTab] = useState(0);
  const [instituteKey, setInstituteKey] = useState('');

  const handleBackButtonClick = () => {
    console.log('Navigating to /login');
    onBackToLogin();
    navigate('/login');
  };  

  // useEffect(() => {
  //   fetchAdminDetails();
  //   // Fetch the list of courses when the component mounts
  //   fetchCourses();
  // }, []);

  // const fetchCourses = async () => {
  //   try {
  //     const response = await axios.get('http://localhost:8080/api/courses');
  //     setCourses(response.data);
  //   } catch (error) {
  //     console.error('Error fetching courses:', error.message);
  //   }
  // };

  useEffect(() => {
    fetchAdminDetails();
    // Fetch the list of courses when the component mounts
    fetchCourses();
  }, [loggedInUser]); // Add loggedInUser as a dependency to trigger the effect when it changes

  const fetchCourses = async () => {
    try {
      if (loggedInUser && loggedInUser.email) {
        // Fetch courses based on the admin's email
        const response = await axios.get(`http://localhost:8080/api/courses?email=${loggedInUser.email}`);
        setCourses(response.data);
      } else {
        // Handle the case where loggedInUser or its email is not available
        console.error('Error: loggedInUser or email is not available');
      }
    } catch (error) {
      console.error('Error fetching courses:', error.message);
    }
  };
  const [adminDetails, setAdminDetails] = useState({});
  const fetchAdminDetails = async () => {
    try {
      // Use the logged-in user's email to fetch admin details
      const response = await axios.get(`http://localhost:8080/api/admin?email=${loggedInUser.email}`);
      setAdminDetails(response.data);
      // Set the instituteKey state with the fetched value
      setInstituteKey(response.data.instituteKey);
    } catch (error) {
      console.error('Error fetching admin details:', error.message);
    }
  };


  const addCourse = async () => {
    try {
      // Validate input
      if (!courseName || !courseFees || !courseDuration || !batch || !strengthOfStudents) {
        alert('Please fill in all fields.');
        return;
      }
  
      // Create a new course object with user details
      const newCourse = {
        instituteKey: instituteKey,
        name: courseName,
        fees: courseFees,
        duration: courseDuration,
        batch: batch,
        strengthOfStudents: strengthOfStudents,
      };
  
      // Send the new course data to the backend
      const response = await axios.post('http://localhost:8080/api/add-course', newCourse);
  
      // Handle the response as needed
      console.log('Course added successfully:', response.data);
  
      // Clear input fields
      setCourseName('');
      setCourseFees('');
      setCourseDuration('');
      setBatch('');
      setStrengthOfStudents('');
  
      // Refresh the course list
      fetchCourses();
    } catch (error) {
      console.error('Error adding course:', error.message);
      // Handle errors here
    }
  };
  


  const handleTabChange = (event, newValue) => {
    setSelectedTab(newValue);
  };

  const styles = {
    container: {
      textAlign: 'center',
      // maxWidth: '400px',
      margin: 'auto',
      marginTop: '50px',
      padding: '20px',
      // marginLeft: '300px',
      boxShadow: '0 0 10px rgba(0, 0, 0, 0.1)',
      borderRadius: '3px',
      backgroundColor: 'White'
    },
  };

  return (
    <div style={{
      backgroundImage: 'url("https://t4.ftcdn.net/jpg/05/24/00/19/240_F_524001951_3pFrgt8uaKLpiSuOhCK5Vl4Dhc5VDOOS.jpg")',
      backgroundSize: 'cover',
      backgroundPosition: 'center',
      minHeight: '100vh',
      overflow: 'hidden',
    }}>
     {/* <Container maxWidth="md" style={{ marginTop: '100px', padding: '20px' }}> */}
      <Container style={styles.container}>
      <h2><p>Welcome {loggedInUser ? loggedInUser.email : 'Loading...'}</p></h2>
      
      <Typography  variant="h4" component="h4" gutterBottom>
        Admin Dashboard
      </Typography>
      <Button sx={{ position: 'absolute', top: 59, left: 50, padding: '10px' }} variant="contained" onClick={handleBackButtonClick} style={{ backgroundColor: 'Black', marginBottom: '20px', Marginleft:'0px' }}>
          Back to Login
        </Button>
      <Tabs value={selectedTab} onChange={handleTabChange} >
        <Tab label="Add Course" />
        <Tab label="Course List" />
      </Tabs>
      <Box>
        {selectedTab === 0 && (
          <>
            <Typography align="center" variant="h5" component="h5" gutterBottom>
              Add Course
            </Typography>
            <Box sx={{ justifyContent: 'center', border: '1px solid lightgray', margin: '50px'}}>
              <Table>
                <TableBody>
                  <TableRow>
                  <div style={{ display: 'flex', flexDirection: 'row', margin: '40px' }}>
                        <TextField
                          label="Institute Key"
                          variant="outlined"
                          size="small"
                          value={instituteKey}
                          disabled // make it disabled to prevent user input
                          style={{ marginBottom: '10px' }}
                        />&nbsp;&nbsp;
                      <TextField
                        label="Batch"
                        variant="outlined"
                        size="small"
                        value={batch}
                        onChange={(e) => setBatch(e.target.value)}
                        style={{ marginBottom: '10px' }}
                      />&nbsp;&nbsp;
                      <TextField
                        label="Course Name"
                        variant="outlined"
                        value={courseName}
                        size="small"
                        onChange={(e) => setCourseName(e.target.value)}
                        style={{ marginBottom: '10px' }}
                      />&nbsp;&nbsp;
                      <TextField
                        label="Course Fees"
                        variant="outlined"
                        value={courseFees}
                        size="small"
                        onChange={(e) => setCourseFees(e.target.value)}
                        style={{ marginBottom: '10px' }}
                      />&nbsp;&nbsp;
                    </div>
                    <div style={{ display: 'flex', flexDirection: 'row', margin: '40px' }}>
                      <TextField
                        label="Course Duration"
                        variant="outlined"
                        size="small"
                        value={courseDuration}
                        onChange={(e) => setCourseDuration(e.target.value)}
                      />&nbsp;&nbsp;
                      <TextField
                        label="Strength of Students"
                        variant="outlined"
                        value={strengthOfStudents}
                        size="small"
                        onChange={(e) => setStrengthOfStudents(e.target.value)}
                      />&nbsp;&nbsp;
                      <Button
                        size="small"
                        variant="contained"
                        onClick={addCourse}
                        style={{ backgroundColor: 'Black'}}
                      >
                        Add Course
                      </Button>&nbsp;&nbsp;
                    </div>
                  </TableRow>
                </TableBody>
              </Table>
            </Box>
          </>
        )}
        {selectedTab === 1 && (
          <div>
            <Typography align="center" variant="h5" component="h5" gutterBottom>
              Course List
            </Typography>
            <TableContainer component={Paper} >
            <Table>
              <TableHead >
                <TableRow>
                  <TableCell>Course Name</TableCell>
                  <TableCell>Course Fees</TableCell>
                  <TableCell>Course Duration</TableCell>
                  <TableCell>Batch</TableCell>
                  <TableCell>Strength Of Students</TableCell>                  
                  <TableCell>Institute Key</TableCell>
                </TableRow>
              </TableHead>
                <TableBody>
                  {courses.map((course) => (
                    <TableRow key={course.id}>
                      <TableCell>{course.name}</TableCell>
                      <TableCell>{course.fees}</TableCell>
                      <TableCell>{course.duration}</TableCell>
                      <TableCell>{course.batch}</TableCell>
                      <TableCell>{course.strengthOfStudents}</TableCell>
                      <TableCell>{course.instituteKey}</TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
            </div>
        )}
      </Box>
    </Container>
    </div>
  );
};

export default AdminDashboard;
