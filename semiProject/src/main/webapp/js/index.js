/* Footer */
createFooterLink();

function createFooterLink() {
	const kangFooterLinksWrap = document.querySelector("#kangFooterLinksWrap");
	

	kangFooterLinksWrap.innerHTML += `
		<div class="col-lg-3 col-md-6 mb-4 mb-md-0">
			<h5 class="text-uppercase">Park W.G</h5>
			<ul class="list-unstyled mb-0">
				<li><i class="xi-youtube-play"></i>
				    <a href="https://www.youtube.com/user/hiparkwg/featured" class="text-white"> YouTube</a>
				</li>
				<li><i class="xi-naver-square"></i>
				    <a href="https://cafe.naver.com/itdocument" class="text-white">Naver cafe</a></li>
				<li><i class="xi-chrome"></i>
				    <a href="http://jobtc.tistory.com/" class="text-white">T story</a></li>
				<li><i class="xi-school"></i>
				    <a href="https://githrd.cafe24.com/student/index.php" class="text-white">Global IT</a></li>
			</ul>
		</div>
		<div class="col-lg-3 col-md-6 mb-4 mb-md-0">
			<h5 class="text-uppercase">Instagram</h5>
			<ul class="list-unstyled mb-0">
				<li><i class="xi-instagram"></i>
				    <a href="https://www.instagram.com/joey_baik501" class="text-white">백재민</a></li>
				<li><i class="xi-instagram"></i>
				    <a href="https://www.instagram.com/dogjinsoon" class="text-white">조성호</a></li>
				<li><i class="xi-instagram"></i>
				    <a href="https://www.instagram.com/sjsoenfbje" class="text-white">설진우</a></li>
				<li><i class="xi-instagram"></i>
				    <a href="https://www.instagram.com/yellow__support" class="text-white">황지원</a></li>
			</ul>
		</div>
		<div class="col-lg-3 col-md-6 mb-4 mb-md-0">
			<h5 class="text-uppercase">Instagram</h5>
			<ul class="list-unstyled mb-0">
				<li><i class="xi-instagram"></i>
				    <a href="https://www.instagram.com/yohan.j_park" class="text-white">박요한</a></li>
				<li><i class="xi-instagram"></i>
				    <a href="https://www.instagram.com/sooyaaa__" class="text-white">지수</a></li>
				<li><i class="xi-instagram"></i>
				    <a href="https://www.instagram.com/hm_son7" class="text-white">손흥민</a></li>
				<li><i class="xi-instagram"></i>
				    <a href="https://www.instagram.com/dahyun_98" class="text-white">다현</a></li>
			</ul>
		</div>
		<div class="col-lg-3 col-md-6 mb-4 mb-md-0">
			<h5 class="text-uppercase">Valueable Site</h5>
			<ul class="list-unstyled mb-0">
				<li><i class="xi-github"></i>
				    <a href="https://github.com/" class="text-white">GitHub</a></li>
				<li><i class="xi-new"></i>
				    <a href="https://www.notion.so/ko-kr" class="text-white">Notion</a></li>
				<li><i class="xi-globus"></i>
				    <a href="https://www.oracle.com/kr/" class="text-white">Oracle</a></li>
				<li><i class="xi-globus"></i>
				    <a href="https://okky.kr/" class="text-white">Okky</a></li>
			</ul>
		</div>
		
	`;
}