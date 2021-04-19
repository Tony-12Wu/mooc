Toast = {
  success: function (message) {
    Swal.fire({
      position: 'top-end',
      icon: 'success',
      title: message,
      showConfirmButton: false,
      timer: 2500
    })
  },
  warning: function (message) {
    Swal.fire({
      position: 'top-end',
      icon: 'warning',
      title: message,
      showConfirmButton: false,
      timer: 1500
    })
  },
  error: function (message) {
    Swal.fire({
      position: 'top-end',
      icon: 'error',
      title: message,
      showConfirmButton: false,
      timer: 1500
    })
  }
  
}