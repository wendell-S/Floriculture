
function showAndHidePopup(element) {
    const timeout = parseInt(element.attr('data-timeout')) || 5000;
    element.fadeIn('fast', function() {
      setTimeout(function() {
        element.fadeOut('fast');
      }, timeout);
    });
  }

  $(document).ready(function() {
    const errorPopup = $('.show-and-hide');
    if (errorPopup.length > 0) {
      showAndHidePopup(errorPopup);
    }
  });