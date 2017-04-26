--raw = LOAD '/home/hadoop/Juilee/Project/AmazonReview/pig_files/summarization_category' USING PigStorage(',') AS (asin:chararray, average:float);

raw = LOAD '/review_summarization/out/part-r-00000' USING PigStorage(',') AS (asin:chararray, average:float);
--------------------------	FIRST JOIN -------------------------------------------- 

--toJoin = LOAD '/home/hadoop/Juilee/Project/AmazonReview/pig_files/category1' USING PigStorage('\t') AS (category:chararray, asin:chararray);

toJoin = LOAD '/category_partitions/part-r-00006' USING PigStorage('\t') AS (category:chararray, asin:chararray);
joinedList = JOIN raw BY asin, toJoin BY asin;
ordered_list = ORDER joinedList BY average DESC;
top_10_items_first = LIMIT ordered_list 10;

STORE top_10_items_first INTO '/pig_temp/category-7' USING PigStorage();

--STORE top_10_items_first INTO '/home/hadoop/Juilee/Project/AmazonReview/top10_cat1' USING PigStorage();
--STORE raw INTO '/pig_join' USING PigStorage();
