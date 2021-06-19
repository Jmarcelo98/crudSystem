const proxy = [
  {
    context: '/cliente',
    target: 'http://localhost:8080/',
    pathRewrite: {'^/cliente' : ''}
  }
];
module.exports = proxy;
