SELECT g.genre_id, g.genre_name, count(g.genre_id) as genre_count
	FROM public.genre g
INNER JOIN book_has_genres bg ON bg.genre_id = g.genre_id
INNER JOIN book b ON b.book_id = bg.book_id
INNER JOIN "language" l ON b.language_id = l.language_id
	WHERE b.language_id = 1 OR l.language_tag=null
GROUP BY g.genre_id
	ORDER BY genre_count DESC
	LIMIT 20;