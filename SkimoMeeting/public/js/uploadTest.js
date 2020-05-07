
function uploadFile() {
  $.ajax({
    url: "/video-upload",
    type: "POST",
    data: new FormData($("#upload-file-form")[0]),
    enctype: 'multipart/form-data',
    processData: false,
    contentType: false,
    cache: false,
    success: function () {
      // Handle upload success
      // ...
    },
    error: function () {
      // Handle upload error
      // ...
    }
  });
}