/* Carousel */
createCarousel();

function createCarousel() {
	const carousel = document.querySelector("#carouselExampleFade");
	const carouselInner = document.querySelector(".carousel-inner");
	
	for(let i=1; i<=11; i++) {
		if(i == 1) {
			carouselInner.innerHTML += `
				<div class="carousel-item active">
					<img src="img/carousel${i}.jpg.webp" class="d-block w-100" alt="img/carousel${i}.jpg.webp">
				</div>
			`;
		} else {
			carouselInner.innerHTML += `
				<div class="carousel-item">
					<img src="img/carousel${i}.jpg.webp" class="d-block w-100" alt="img/carousel${i}.jpg.webp">
				</div>
			`;
		}
	}
	
	carousel.innerHTML += `
		<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span>
			<span class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span>
			<span class="visually-hidden">Next</span>
		</button>
	`;
}