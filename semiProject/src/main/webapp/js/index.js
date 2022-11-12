/* Footer */
createFooterLink();

function createFooterLink() {
	const kangLinksWrap = document.querySelector("#kangLinksWrap");
	
	for(let i=0; i<4; i++) {
		kangLinksWrap.innerHTML += `
			<div class="col-lg-3 col-md-6 mb-4 mb-md-0">
				<h5 class="text-uppercase">Links</h5>
				<ul class="list-unstyled mb-0">
					<li><a href="#" class="text-white">Link 1</a></li>
					<li><a href="#" class="text-white">Link 2</a></li>
					<li><a href="#" class="text-white">Link 3</a></li>
					<li><a href="#" class="text-white">Link 4</a></li>
				</ul>
			</div>
		`;
	}
}