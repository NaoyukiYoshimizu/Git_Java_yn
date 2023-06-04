window.addEventListener('DOMContentLoaded', () => {
  document.querySelector('#ch_flag').addEventListener('click', evt => {
	let submit_button = document.querySelector('#submit');
	submit_button.disabled = true;
	if (evt.currentTarget.checked) {
		submit_button.disabled = false;
	}
  });
});