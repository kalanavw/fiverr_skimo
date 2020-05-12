function _(el) {
  return document.getElementById(el);
}

function uploadFile() {
//  var file = _("file").files[0];
var file = document.getElementById('file').files[0];
  var formData = new FormData();
  formData.append("file", file);
  console.log(file);
//  var ajax = new XMLHttpRequest();
/*  ajax.upload.addEventListener("progress", progressHandler, true);
  ajax.addEventListener("load", completeHandler, false);
  ajax.addEventListener("error", errorHandler, false);
  ajax.addEventListener("abort", abortHandler, false);
  ajax.setRequestHeader("content-type", "multipart/form-data")
  ajax.open("POST", "/video-upload"); // http://www.developphp.com/video/JavaScript/File-Upload-Progress-Bar-Meter-Tutorial-Ajax-PHP
  //use file_upload_parser.php from above url
  ajax.send(formData);*/
 /* $.ajax({
      xhr: function() {
          var xhr = new window.XMLHttpRequest();
          xhr.upload.addEventListener("progress", function(evt) {
              if (evt.lengthComputable) {
                  var percentComplete = evt.loaded / evt.total;
                  //Do something with upload progress here
                  console.log(percentComplete);
                  progressHandler(evt);
              }
         }, false);

         xhr.addEventListener("progress", function(evt) {
             if (evt.lengthComputable) {
                 var percentComplete = evt.loaded / evt.total;
                                   console.log(percentComplete);
                 //Do something with download progress
             }
         }, false);
         xhr.addEventListener("load", function(evt){
            completeHandler(evt);
         }, false);
          xhr.addEventListener("error", function(evt){
            errorHandler(evt)
          }, false);
          xhr.addEventListener("abort", function(evt){
            abortHandler(evt);
          }, false);

         return xhr;
      },
     async: true,
     data: formData,
     cache: false,
     contentType: false,
     processData: false,
     timeout: 60000,
     success: function(data){
          //Do something on success
                            console.log('suceess');
      }
  });
  };*/

      $.ajax({
          type: "POST",
          url: "script",
          xhr: function () {
              var myXhr = $.ajaxSettings.xhr();
              if (myXhr.upload) {
                  myXhr.upload.addEventListener('progress', progressHandler, false);
              }
              xhr.addEventListener("load", completeHandler, false);
               xhr.addEventListener("error", errorHandler, false);
               xhr.addEventListener("abort", abortHandler, false);
              return myXhr;
          },
          success: function (data) {
              // your callback here
          },
          error: function (error) {
              // handle error
          },
          async: true,
          data: formData,
          cache: false,
          contentType: false,
          processData: false,
          timeout: 60000
      });

}

function progressHandler(event) {
  _("loaded_n_total").innerHTML = "Uploaded " + event.loaded + " bytes of " + event.total;
  var percent = (event.loaded / event.total) * 100;
  _("progressBar").value = Math.round(percent);
  _("status").innerHTML = Math.round(percent) + "% uploaded... please wait";
}

function completeHandler(event) {
  _("status").innerHTML = event.target.responseText;
  _("progressBar").value = 0; //wil clear progress bar after successful upload
}

function errorHandler(event) {
  _("status").innerHTML = "Upload Failed";
}

function abortHandler(event) {
  _("status").innerHTML = "Upload Aborted";
}




//---------------------------------------------------------------

/*var Upload = function (file) {
    this.file = file;
};
Upload.prototype.getType = function() {
    return this.file.type;
};
Upload.prototype.getSize = function() {
    return this.file.size;
};
Upload.prototype.getName = function() {
    return this.file.name;
};
Upload.prototype.doUpload = function () {
    var that = this;
    var formData = new FormData();
console.log( this.file);
    // add assoc key values, this will be posts values
    formData.append("file", this.file);

    $.ajax({
        type: "POST",
        url: "script",
        xhr: function () {
            var myXhr = $.ajaxSettings.xhr();
            if (myXhr.upload) {
                myXhr.upload.addEventListener('progress', that.progressHandling, false);
            }
            return myXhr;
        },
        success: function (data) {
            // your callback here
        },
        error: function (error) {
            // handle error
        },
        async: true,
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        timeout: 60000
    });
};

Upload.prototype.progressHandling = function (event) {
    var percent = 0;
    var position = event.loaded || event.position;
    var total = event.total;
    var progress_bar_id = "#progress-wrp";
    if (event.lengthComputable) {
        percent = Math.ceil(position / total * 100);
    }
    // update progressbars classes so it fits your code
    $(progress_bar_id + " .progress-bar").css("width", +percent + "%");
    $(progress_bar_id + " .status").text(percent + "%");
};*/
