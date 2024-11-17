$('button.details-toggler').on('click', e => {
  const movieId = $(e.target)
    .attr('id')
    .replace(/^(close-)?details-btn__/, '');
  const detailsEl = $(`#details__${movieId}`);

  if (detailsEl.is(':visible')) {
    detailsEl.slideUp();
    $(`#close-details-btn__${movieId}`).hide();
    $(`#details-btn__${movieId}`).show();
  } else {
    $(`#close-details-btn__${movieId}`).show();
    $(`#details-btn__${movieId}`).hide();
  }
});

$('#new-movie__btn').on('click', _ => {
  $('#new-movie__btn').hide();
});
