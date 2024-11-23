const userData = JSON.parse(localStorage.getItem('user'));

if (userData) {
  const userId = userData.id; // Retrieve the 'id' field
  //console.log(userId); // Output the user ID
} else {
  console.log("No user found in localStorage");
}

export default userData