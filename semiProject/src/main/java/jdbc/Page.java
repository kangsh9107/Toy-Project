package jdbc;

public class Page {
	// 페이지와 관련된 필드 선언
	String findStr = "";
	int    nowPage = 1;
	int    totSize;
	int    listSize = 10;
	int    blockSize = 5;
	int    totPage, startPage, endPage;
	int    startNo, endNo;
	
	public void setDate(int nowPage, int totSize) {
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
		startNo = endNo - listSize;              // 다른 데이터베이스는 +1을 한다. 하지만 mysql의 limit는 제로베이스라서 +1을 하면 리스트에서 2번째 데이터부터 나온다.
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
	
}
