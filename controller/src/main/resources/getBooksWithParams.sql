SELECT  b.book_id,
		b.book_year,
		b.book_rating, 
		b.book_price,
		b.book_age_restriction, 
		b.book_thumbnail_url, 
		b.book_title, 
		b.language_id, 
l.language_id,
l.language_name,
l.language_tag,

a.author_id,
a.author_description,
a.author_first_name,
a.author_last_name,
a.author_wiki_link,
/*
t.author_id,
t.author_description,
t.author_first_name,
t.author_last_name,
t.author_wiki_link,*/

g.genre_id,
g.genre_name
		
	FROM public.book b
INNER JOIN public."language" l on b.language_id = l.language_id

LEFT JOIN public.book_has_authors ba on b.book_id = ba.book_id
INNER JOIN public.author a on a.author_id = ba.author_id

LEFT JOIN public.book_has_genres bg on b.book_id = bg.book_id
INNER JOIN public.genre g on g.genre_id = bg.genre_id

LEFT JOIN public.book_has_translators bt on b.book_id = bt.book_id
INNER JOIN public.author t on t.author_id = bt.author_id

WHERE  a.author_id IN (7)
		/*(l.language_id=1 OR l.language_tag='US_en') 
	AND (b.book_rating>0 AND b.book_rating<100)
	AND g.genre_id IN (1643)
				 AND (b.book_year>0 AND b.book_year<2020) 
	
		AND		 */
/* 	AND (b.book_price>0 AND b.book_price<40) */
		/*AND t.author_id IN (1939)*/ 
	

		

		
			
	