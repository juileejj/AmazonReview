summarized = LOAD '/review_summarization/out/part-r-00000' USING PigStorage(',') AS (asin:chararray, average:float);
cate = LOAD '/category_partitions/part-r-00009' USING PigStorage('\t') AS (category:chararray, asin:chararray);
join = JOIN summarized BY asin, cate BY asin;
list = ORDER join BY average DESC;
top_10_items_first = LIMIT list 10;
STORE top_10_items_first INTO '/pig_temp/category-10' USING PigStorage();
