 EXPLAIN EXTENDED  select * from page_info_list where siteConfigSeq > 0 and statCd = '99' limit 5000; SET PROFILING=1;  SET PROFILING_HISTORY_SIZE=30; select pageInfoListSeq, siteConfigSeq, goodsPrice, goodsUrl  from page_info_list  where siteConfigSeq > 0 and statCd = '99'  and updateDt < sysdate() limit 5000;  SELECT pageInfoListSeq,goodsPrice, goodsUrlFROM zebra.page_info_list; show profiles; show profile for query 51; 