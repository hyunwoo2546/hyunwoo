package com.hyunwoo.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hyunwoo.vo.BoardVO;
import com.hyunwoo.vo.Criteria;


@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession sqlSession;
	
	/**** 게시글 작성 ****/
	@Override
	public void write(BoardVO boardVO) throws Exception {
		sqlSession.insert("boardMapper.insert", boardVO);
		
	}
	
	/**** 게시물 목록 조회 ****/
	/*
	 * return sqlSession.selectList("boardMapper.list");에서 boardMapper는 mapper의
	 * namespace가 boardMapper이고 그중 id가 list인 것을 가져와 반환하라.
	 * ,cri / ,bno / ,boardVO 등은 클래스에 인자값으로 적어준다. 뜻은 인자값을 참조하라는 뜻.
	 */
	@Override
	public List<BoardVO> list(Criteria cri) throws Exception {
	
		return sqlSession.selectList("boardMapper.listPage", cri);
	}
	
	/*** 게시물 총 갯수 ***/
	@Override
	public int listCount() throws Exception {
		return sqlSession.selectOne("boardMapper.listCount");
	}
	
	/**** 게시물 조회 ****/
	@Override
	public BoardVO read(int bno) throws Exception {
		return sqlSession.selectOne("boardMapper.read",bno);
	}
	
	/**** 게시물 수정 ****/
	@Override
	public void update(BoardVO boardVO) throws Exception {
		sqlSession.update("boardMapper.update",boardVO);
	}
	
	/**** 게시물 삭제 ****/
	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete("boardMapper.delete",bno);
	}


}























/*
 * import javax.inject.Inject;
 * 
 * import org.apache.ibatis.session.SqlSession; import
 * org.springframework.stereotype.Repository;
 * 
 * import com.hyunwoo.vo.BoardVO;
 * 
 * @Repository public class BoardDAOImpl implements BoardDAO{
 * 
 * @Inject private SqlSession sqlSession;
 * 
 * //게시글작성
 * 
 * @Override public void write(BoardVO boardVO) throws Exception {
 * sqlSession.insert("BoardMapper.insert",boardVO); }
 * 
 * }
 */