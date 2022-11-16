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

/* Best8 */

const kangBestWrap2 = document.querySelector("#kangBestWrap");
if(kangBestWrap != null) {
	showBest();
	
	function showBest() {
		for(let i=0; i<8; i++) {
			kangBestWrap.innerHTML += `
				<div class="col mb-5">
					<div class="card h-100">
						<!-- Sale badge-->
						<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Best</div>
						<!-- Product image-->
						<img class="card-img-top" src="img/outer${i+62}.png" alt="outer${i+62}.png" />
						<div class="card-body p-4">
							<div class="text-center">
								<!-- Product name -->
								<h5 class="fw-bolder">Outer${i+62}</h5>
								<!-- Product price -->
								₩ 2,500,000
							</div>
						</div>
						<!-- Buy -->
						<div class="card-footer p-2 pt-0 border-top-0 bg-transparent">
							<div class="text-center">
								<a class="btn btn-outline-dark" href="#">Buy</a>
								<a class="btn btn-outline-dark">Add to cart</a>
							</div>
						</div>
					</div>
				</div>
			`;
		}
	}
}