package jdbc;

public class ProductPage {
	String findStr   = ""; // 검색어
	int    nowPage   = 1;  // 현재 페이지
	int    totSize;        // 검색된 데이터 건수
	int    listSize  = 8; // 한 페이지에 노출될 데이터 건수
	int    blockSize = 3;  // 한 페이지에 노출시킬 페이지 버튼 건수
	int    totPage;        // 전체 페이지 건수
	int    startPage;      // 표시된 페이지 버튼 중 처음 페이지 버튼
	int    endPage;        // 표시된 페이지 버튼 중 마지막 페이지 버튼
	int    startNo;        // 한 페이지에 노출될 데이터의 시작 데이터
	int    endNo;          // 한 페이지에 노출될 데이터의 마지막 데이터
	String category  = ""; // 노출 시킬 카테고리 종류

	public void setPage(int nowPage, int totSize) {
		this.nowPage = nowPage;
		this.totSize = totSize;
		
		compute();
	}

	public void compute() {
		totPage = (int)Math.ceil( (double)totSize / listSize );
		endPage = (int)Math.ceil( (double)nowPage / blockSize ) * blockSize;
		startPage = endPage - blockSize + 1;
		if(endPage > totPage) endPage = totPage; // 보정작업
		
		endNo = nowPage * listSize;
		startNo = endNo - listSize;
		if(endNo > totSize) endNo = totSize;     // 보정작업
	}

	public String getFindStr() { return findStr; }
	public int getNowPage() { return nowPage; }
	public int getTotSize() { return totSize; }
	public int getListSize() { return listSize; }
	public int getBlockSize() { return blockSize; }
	public int getTotPage() { return totPage; }
	public int getStartPage() { return startPage; }
	public int getEndPage() { return endPage; }
	public int getStartNo() { return startNo; }
	public int getEndNo() { return endNo; }
	public String getCategory() { return category; }

	public void setFindStr(String findStr) { this.findStr = findStr; }
	public void setNowPage(int nowPage) { this.nowPage = nowPage; }
	public void setTotSize(int totSize) { this.totSize = totSize; }
	public void setListSize(int listSize) { this.listSize = listSize; }
	public void setBlockSize(int blockSize) { this.blockSize = blockSize; }
	public void setTotPage(int totPage) { this.totPage = totPage; }
	public void setStartPage(int startPage) { this.startPage = startPage; }
	public void setEndPage(int endPage) { this.endPage = endPage; }
	public void setStartNo(int startNo) { this.startNo = startNo; }
	public void setEndNo(int endNo) { this.endNo = endNo; }
	public void setCategory(String category) { this.category = category; }
}
