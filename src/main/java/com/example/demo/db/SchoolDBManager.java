package com.example.demo.db;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.example.demo.vo.EducationVO;
import com.example.demo.vo.LectureVO;
import com.example.demo.vo.LikedVO;
import com.example.demo.vo.OpinionVO;
import com.example.demo.vo.TrainingRequestVO;


public class SchoolDBManager extends DBManager{

	// lecture
		
		// 강의 전체목록 반환 
		public static List<LectureVO> findAllLecture () {
			SqlSession session = sqlSessionFactory.openSession();
			List<LectureVO> list = session.selectList("lecture.findAllLecture");
			session.close();
			return list;
		}
		
		public static LectureVO findByNoLecture(int lecNO){
			System.out.println("dbManager) findBYNO: "+lecNO);
			LectureVO l = null;
			SqlSession session = sqlSessionFactory.openSession();
			l = session.selectOne("lecture.findByNoLecture", lecNO);
			session.close();
			return l;
		}
		
		public static int insertLecture (LectureVO l) {
			int re = -1;
			SqlSession session=sqlSessionFactory.openSession();
			re = session.insert("lecture.insertLecture", l);
			session.commit();
			session.close();
			return re;
		}
		// 강연좋아요 추가
		public static int insertLectureLiked(HashMap<String, Object> map) {
			int re = -1;
			SqlSession session = sqlSessionFactory.openSession();
			re = session.insert("liked.insertLectureLiked",map);
			session.commit();
			session.close();
			return re;
		}
		// 강연좋아요 취소
		public static int deleteLectureLiked(HashMap<String, Object> map) {
			int re = -1;
			SqlSession session =sqlSessionFactory.openSession(true);
			re = session.delete("liked.deleteLectureLiked",map);
			session.close();
			return re;
		}
		// 강연 좋아요목록 조회
		public static List<LectureVO> findLikedLectureByUserNo (int userno) {
			SqlSession session = sqlSessionFactory.openSession();
			List<LectureVO> list = session.selectList("liked.findLikedLectureByUserNo", userno);
			session.close();
			return list;
		}
		
		// 좋아요한 lecNO 조회( 화면에 세션유지를 위함)
		public static List<Integer> findLikedLectureNos(int userno) {
			SqlSession session = sqlSessionFactory.openSession();
			List<Integer> list = session.selectList("liked.findLikedLectureNos", userno);
			session.close();
			return list;
		}
		
		/* JPA 사용으로 삭제예정
		public static int updateLecture(LectureVO l) {
			int re = -1;
			SqlSession session = sqlSessionFactory.openSession(true);
			re = session.update("lecture.updateLecture",l);
			session.close();
			return re;
		}
		
		public static int deleteLecture(LectureVO l) {
			int re = -1;
			SqlSession session =sqlSessionFactory.openSession(true);
			re = session.delete("lecture.deleteLecture",l);
			session.close();
			return re;
		}
		*/
	
		
// education
		//기본 레코드수	
		public static int getTotalRecordEducation(HashMap<String, Object> map)	{
			int n = 0;
			SqlSession session = sqlSessionFactory.openSession();
			n = session.selectOne("education.getTotalRecordEducation", map);
			session.close();
			return n;
		}
		
		// 기본 findAll
		public static List<EducationVO> findAllEducation(HashMap<String, Object> map){
			SqlSession session = sqlSessionFactory.openSession();
			List<EducationVO> list = session.selectList("education.findAllEducation",map);
			session.close();
			return list;
		}
		
		
		public static EducationVO findByNoEducation (int eduno) {
			EducationVO e = null;
			SqlSession session = sqlSessionFactory.openSession();
			e = session.selectOne("education.findByNoEducation", eduno);
			session.close();
			return e;
		}
		
		// 교육좋아요 추가
		public static int insertEducationLiked(HashMap<String, Object> map) {
			int re = -1;
			System.out.println("dbmanager liked map : "+map);
			SqlSession session = sqlSessionFactory.openSession();
			re = session.insert("liked.insertEducationLiked",map);
			session.commit();
			session.close();
			return re;
		}
		// 교육좋아요 취소
		public static int deleteEducationLiked(HashMap<String, Object> map) {
			System.out.println("dbmanager liked map : "+map);
			int re = -1;
			SqlSession session =sqlSessionFactory.openSession(true);
			re = session.delete("liked.deleteEducationLiked",map);
			session.close();
			return re;
		}
		// 교육 좋아요목록 조회
		public static List<EducationVO> findLikedEducationByUserNo (int userno) {
			SqlSession session = sqlSessionFactory.openSession();
			List<EducationVO> list = session.selectList("liked.findLikedEducationByUserNo", userno);
			session.close();
			return list;
		}
		// 좋아요한 eduNO 조회
		public static List<Integer> findLikedEducationNos(int userno) {
			SqlSession session = sqlSessionFactory.openSession();
			List<Integer> list = session.selectList("liked.findLikedEducationNos", userno);
			session.close();
			return list;
		}

	
	// TrainingRequest
		public static int getTotalRecordTrainingRequest()	{
			int reqNo = 0;
			SqlSession session = sqlSessionFactory.openSession();
			reqNo = session.selectOne("trainingRequest.getTotalRecordTrainingRequest");
			session.close();
			return reqNo;
		}
		public static List<TrainingRequestVO> findAllTrainingRequest(HashMap<String, Object> map){
			SqlSession session = sqlSessionFactory.openSession();
			List<TrainingRequestVO> list = session.selectList("trainingRequest.findAllTrainingRequest", map);
			session.close();
			return list;
		}
		
		public static TrainingRequestVO findByNoTrainingRequest (int reqNo) {
			TrainingRequestVO t = null;
			SqlSession session = sqlSessionFactory.openSession();
			t = session.selectOne("trainingRequest.findByNoTrainingRequest", reqNo);
			session.close();
			return t;
		}
		
		
		/* JPA 사용으로 삭제예정
		public static int insertTrainingRequest (TrainingRequestVO t) {
			int re = -1;
			SqlSession session=sqlSessionFactory.openSession();
			re = session.insert("trainingRequest.insertTrainingRequest", t);
			session.commit();
			session.close();
			return re;
		}
		
		public static int updateTrainingRequest(TrainingRequestVO t) {
			int re = -1;
			SqlSession session = sqlSessionFactory.openSession(true);
			re = session.update("trainingRequest.updateTrainingRequest",t);
			session.close();
			return re;
		}
		
		public static int deleteTrainingRequest(TrainingRequestVO t) {
			int re = -1;
			SqlSession session =sqlSessionFactory.openSession(true);
			re = session.delete("trainingRequest.deleteTrainingRequest",t);
			session.close();
			return re;
		}
		*/
	// opinion
		public static int getTotalRecordOpinion()	{
			int opinionNo = 0;
			SqlSession session = sqlSessionFactory.openSession();
			opinionNo = session.selectOne("opinion.getTotalRecordOpinion");
			session.close();
			return opinionNo;
		}
		public static List<OpinionVO> findAllOpinion(HashMap<String, Object> map){
			SqlSession session = sqlSessionFactory.openSession();
			List<OpinionVO> list = session.selectList("opinion.findAllOpinion", map);
			session.close();
			return list;
		}
		
		public static OpinionVO findByNoOpinion (int opinionNO) {
			OpinionVO o = null;
			SqlSession session = sqlSessionFactory.openSession();
			o = session.selectOne("opinion.findByNoOpinion", opinionNO);
			session.close();
			return o;
		}
		
		public static int insertOpinion (OpinionVO o) {
			int re = -1;
			SqlSession session=sqlSessionFactory.openSession();
			re = session.insert("opinion.insertOpinion", o);
			session.commit();
			session.close();
			return re;
		}
	
		
		public static int updateOpinion(OpinionVO o) {
			int re = -1;
			SqlSession session = sqlSessionFactory.openSession(true);
			re = session.update("opinion.updateOpinion",o);
			session.close();
			return re;
		}
		
		public static int deleteOpinion(OpinionVO o) {
			int re = -1;
			SqlSession session =sqlSessionFactory.openSession(true);
			re = session.delete("opinion.deleteOpinion",o);
			session.close();
			return re;
		}

	

	
		
}