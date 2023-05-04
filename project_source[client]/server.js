const express = require('express');
const path = require('path');
const app = express();

//http://localhost:9000/index
app.get('/index', function (req, res) {
  res.sendFile(path.join(__dirname, 'public', 'index.html'));
});
//http://localhost:9000/logo
app.use('/logo',express.static(path.join(__dirname, 'public/resources/images/logo.png')));

app.listen(9000);