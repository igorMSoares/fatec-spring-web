document.addEventListener('htmx:afterSwap', e => {
  if ($(e.detail.target).is(':hidden')) {
    $(e.detail.target).slideDown();
  }
});

