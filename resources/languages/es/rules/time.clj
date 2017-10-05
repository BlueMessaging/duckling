(
  ;; generic
  "<day-of-month> <named-month> del presente año" ; 4 julio
  [(integer 1 31)
   #"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"]
  (intersect (cycle-nth :year 0) (namedMonthToNumber %2) (day-of-month (:value %1)))

  "<day-of-month> de <named-month> del año actual" ; 4 de julio
  [(integer 1 31) 
   #"(?i)de?"
   #"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"]
  (intersect (cycle-nth :year 0) (namedMonthToNumber %3) (day-of-month (:value %1)))

  "<day-of-month> <named-month> <year>" ; 4 julio 81
  [(integer 1 31) 
   #"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"
   (integer -10000 999)]
  (intersect (assoc (year (:value %3)) :latent true) (namedMonthToNumber %2) (day-of-month (:value %1)))

  "<day-of-month> of <named-month> <year>" ; 4 de julio 81
  [(integer 1 31) 
   #"(?i)de"
   #"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"
   (integer -10000 999)]
  (intersect (assoc (year (:value %4)) :latent true) (namedMonthToNumber %3) (day-of-month (:value %1)))
  
  "<day-of-month> of <named-month> <year>" ; 4 de julio del 81
  [(integer 1 31) 
   #"(?i)de"
   #"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"
   #"(?i)del?( el)?"
   (integer -10000 999)]
  (intersect (assoc (year (:value %5)) :latent true) (namedMonthToNumber %3) (day-of-month (:value %1)))
  
  "<named-month> <day-of-month> <year>" ; julio 04 81
  [#"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"
   (integer 1 31)
   (integer -10000 999)]
  (intersect (assoc (year (:value %3)) :latent true) (namedMonthToNumber %1) (day-of-month (:value %2)))
  
  "<named-month> <day-of-month> <year>" ; julio 04 del 81
  [#"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"
   (integer 1 31)
   #"(?i)del?( el)?"
   (integer -10000 999)]
  (intersect (assoc (year (:value %4)) :latent true) (namedMonthToNumber %1) (day-of-month (:value %2)))
  
  "<named-month> <day-of-month> <year>" ; julio 04 1981
  [#"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?" 
     (integer 1 31)
     (integer 1000 2100)]
  (intersect (year (:value %3)) (namedMonthToNumber %1) (day-of-month (:value %2)))
  
  "<named-month> <day-of-month> <year>" ; julio 04 de 1981
  [#"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?" 
     (integer 1 31)
   #"(?i)del?( el)?"
     (integer 1000 2100)]
  (intersect (year (:value %4)) (namedMonthToNumber %1) (day-of-month (:value %2)))
  
  "<day-of-month> <named-month> <year>" ; 04 julio 1981
  [(integer 1 31)
  #"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"
  (integer 1000 2100)]
  (intersect (year (:value %3)) (namedMonthToNumber %2) (day-of-month (:value %1)))
  
  "<day-of-month> <named-month> <year>" ; 04 de july 1981
  [(integer 1 31)
  #"(?i)de"
  #"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"
  (integer 1000 2100)]
  (intersect (year (:value %4)) (namedMonthToNumber %3) (day-of-month (:value %1)))
  
  "<day-of-month> <named-month> <year>" ; 04 de july de 1981
  [(integer 1 31)
  #"(?i)de"
  #"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"
  #"(?i)del?( el)?"
  (integer 1000 2100)]
  (intersect (year (:value %5)) (namedMonthToNumber %3) (day-of-month (:value %1)))
  
  ; same thing, with "/" in between like "february/14/1999"
  "intersect by / no latent"
  [#"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"
  #"(?i)(\s?/\s?|\s?-\s?|\s?_\s?|\s?\.\s?|\s?,\s?)"
  (integer 1 31)
  #"(?i)(\s?/\s?|\s?-\s?|\s?_\s?|\s?\.\s?|\s?,\s?)"
  (integer 1000 2100)] ; sequence of two tokens with a time fn
  (intersect (year (:value %5)) (namedMonthToNumber %1) (day-of-month (:value %3)))
  
  ; same thing, with "/" in between like "february/14/99"
  "intersect by / no latent"
  [#"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"
  #"(?i)(\s?/\s?|\s?-\s?|\s?_\s?|\s?\.\s?|\s?,\s?)"
  (integer 1 31)
  #"(?i)(\s?/\s?|\s?-\s?|\s?_\s?|\s?\.\s?|\s?,\s?)"
  (integer -10000 999)] ; sequence of two tokens with a time fn
  (intersect (assoc (year (:value %5)) :latent true) (namedMonthToNumber %1) (day-of-month (:value %3)))
  
  ; same thing, with "/" in between like "14/february/1999"
  "intersect by / no latent"
  [(integer 1 31)
  #"(?i)(\s?/\s?|\s?-\s?|\s?_\s?|\s?\.\s?|\s?,\s?)+"
  #"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"
  #"(?i)(\s?/\s?|\s?-\s?|\s?_\s?|\s?\.\s?|\s?,\s?)"
  (integer 1000 2100)] ; sequence of two tokens with a time fn
  (intersect (year (:value %5)) (namedMonthToNumber %3) (day-of-month (:value %1)))
  
  ; same thing, with "/" in between like "14/february/99"
  "intersect by / no latent"
  [(integer 1 31)
  #"(?i)(\s?/\s?|\s?-\s?|\s?_\s?|\s?\.\s?|\s?,\s?)"
  #"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"
  #"(?i)(\s?/\s?|\s?-\s?|\s?_\s?|\s?\.\s?|\s?,\s?)"
  (integer -10000 999)] ; sequence of two tokens with a time fn
  (intersect (assoc (year (:value %5)) :latent true) (namedMonthToNumber %3) (day-of-month (:value %1)))
  
  ; mostly for January 12, 2005
  ; this is a separate rule, because commas separate very specific tokens
  ; so we want this rule's classifier to learn this
  "intersect by \",\""
  [#"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"
  (integer 1 31)
  #","
  (integer 1000 2100)] ; sequence of two tokens with a time fn
  (intersect (year (:value %4)) (namedMonthToNumber %1) (day-of-month (:value %2)))
  
  ; mostly for January 12, 05
  ; this is a separate rule, because commas separate very specific tokens
  ; so we want this rule's classifier to learn this
  "intersect by \",\""
  [#"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"
  (integer 1 31)
  #","
  (integer -10000 999)] ; sequence of two tokens with a time fn
  (intersect (assoc (year (:value %4)) :latent true) (namedMonthToNumber %1) (day-of-month (:value %2)))
  
  ; Formatted dates and times

  "dd/mm/yyyy"
  #"(3[01]|[12]\d|0?[1-9])\s?[-/_.,\s]+\s?(0?[1-9]|1[0-2])\s?[-/_.,\s]+\s?(\d{2,4})"
  (parse-dmy (first (:groups %1)) (second (:groups %1)) (nth (:groups %1) 2) true)

  "yyyy-mm-dd    stricted to yyyy"
  #"(\d{4})\s?[-/_.,\s]+\s?(0?[1-9]|1[0-2])\s?[-/_.,\s]+\s?(3[01]|[12]\d|0?[1-9])"
  (parse-dmy (nth (:groups %1) 2) (second (:groups %1)) (first (:groups %1)) true)
  
  ;; Special occasions followed by year
  
  "christmas <year>"
  #"(?i)(la )?na[bv]idad (\d{2,4})"
  (parse-dmy "25" "12" (nth (:groups %1) 1) true)
  
  "christmas eve <year>"
  #"(?i)noche\s?[bv]uena (\d{2,4})"
  (parse-dmy "24" "12" (nth (:groups %1) 0) true)
  
  "new year's eve"
  #"(?i)noche\s?[bv]ieja (\d{2,4})"
  (parse-dmy "31" "12" (nth (:groups %1) 0) true)
  
  "new year's day"
  #"(?i)a[nñ]o nue[bv]o (\d{2,4})"
  (parse-dmy "1" "1" (first (:groups %1)) true)

  "valentine's day <year>"
  #"(?i)d[ií]a del? (san valent[ií]n|amor y la amistad) (\d{2,4})"
  (parse-dmy "14" "2" (nth (:groups %1) 1) true)
  
  "dia de la constitucion <year>"
  #"(?i)d[ií]a de (la )?constitu[sc]i[oó]n (\d{2,4})"
  (parse-dmy "5" "2" (second (:groups %1)) true)
  
  "independence day <year>"
  #"(?i)d[ií]a de (la )?independen[sc]ia (\d{2,4})"
  (parse-dmy "15" "9" (second (:groups %1)) true)

  "halloween day <year>"
  #"(?i)[hj]all?owe?en (\d{2,4})"
  (parse-dmy "31" "10" (second (:groups %1)) true)
  
  "dia de las madres <year>"
  #"(?i)d[ií]a de (las? )?madres? (\d{2,4})"
  (parse-dmy "10" "5" (second (:groups %1)) true)
  
  "dia de los muertos <year>"
  #"(?i)d[ií]a de (los? )?muertos (\d{2,4})"
  (parse-dmy "2" "11" (second (:groups %1)) true)
  
  "dia de la revolucion <year>"
  #"(?i)d[ií]a de (la )?re[vb]olu[sc]i[oó]n (\d{2,4})"
  (parse-dmy "21" "11" (second (:groups %1)) true)
  
  "Día de la Virgen de Guadalupe"
  #"(?i)d[ií]a de (la )?[vb]irgen( de guadalupe)? (\d{2,4})"
  (parse-dmy "12" "12" (nth (:groups %1) 2) true)
  
  ;; Relative
  
  "right now"
  #"a?h?or(it)?a|en\s?seguida|cuanto\s?antes|en\s?(este|un)\s?mo(m|n)ento|ya|in?mediat(amente|o)|lo m(a|á)s pron?to"
  (cycle-nth :second 0)
  
  "today"
  #"(?i)(hoy|oy|oi)"
  (cycle-nth :day 0)

  "tomorrow"
  #"(?i)ma(n|ñ|\?)ana"
  (cycle-nth :day 1)

  "yesterday"
  #"(?i)ayer"
  (cycle-nth :day -1)
  
  "the day after tomorrow"
  #"(?i)(pasad[ao]\s?|despu(é|e)s de )ma(n|ñ|\?)ana"
  (cycle-nth :day 2)

  "the day before yesterday"
  #"(?i)ante\s?ayer|antes de (ayer|anoche)|antier"
  (cycle-nth :day -2)
  
  "<special occasion> despues de la que sigue"
  [#"(?i)((d[ií]a del? )?na[bv]idad|noche\s?[bv]uena|noche\s?[bv]ieja|(d[ií]a del? )?a[nñ]o nue[bv]o|(d[ií]a del? )san valent[ií]n|(d[ií]a del? )amor y la amistad|(d[ií]a del? )(la )?constitu[sc]i[oó]n|(d[ií]a del? )(la )?independen[sc]ia|(d[ií]a del? )?[hj]all?owe?en|(d[ií]a del? )(las? )?madres?|(d[ií]a del? )(los? )?muertos|(d[ií]a del? )(la )?re[vb]olu[sc]i[oó]n|(d[ií]a del? )(la )?[vb]irgen( de guadalupe)?)"
  #"(?i)despues (de el|del?)( la)? (que )?(sigue|siguiente|pr[o|ó]ximo)"]
  (pred-nth-not-immediate (holidayToDateEsp %1) 1)
  
  "<special occasion> antepasada"
  [#"(?i)((d[ií]a del? )?na[bv]idad|noche\s?[bv]uena|noche\s?[bv]ieja|(d[ií]a del? )?a[nñ]o nue[bv]o|(d[ií]a del? )san valent[ií]n|(d[ií]a del? )amor y la amistad|(d[ií]a del? )(la )?constitu[sc]i[oó]n|(d[ií]a del? )(la )?independen[sc]ia|(d[ií]a del? )?[hj]all?owe?en|(d[ií]a del? )(las? )?madres?|(d[ií]a del? )(los? )?muertos|(d[ií]a del? )(la )?re[vb]olu[sc]i[oó]n|(d[ií]a del? )(la )?[vb]irgen( de guadalupe)?)"
  #"(?i)(ante\s?pasad[a|o])"]
  (pred-nth (holidayToDateEsp %1) -2)
  
  ; xmas del año pasad[ao]
  "xmas del año pasad[ao]"
  [#"(?i)((d[ií]a del? )?na[bv]idad|noche\s?[bv]uena|noche\s?[bv]ieja|(d[ií]a del? )?a[nñ]o nue[bv]o|(d[ií]a del? )san valent[ií]n|(d[ií]a del? )amor y la amistad|(d[ií]a del? )(la )?constitu[sc]i[oó]n|(d[ií]a del? )(la )?independen[sc]ia|(d[ií]a del? )?[hj]all?owe?en|(d[ií]a del? )(las? )?madres?|(d[ií]a del? )(los? )?muertos|(d[ií]a del? )(la )?re[vb]olu[sc]i[oó]n|(d[ií]a del? )(la )?[vb]irgen( de guadalupe)?)"
  #"(?i)(de el |del )?a[nñ]o (pasad[ao]|anterior|que paso)"] ; sequence of two tokens with a time fn
  (intersect (cycle-nth-after :year -1 (cycle-nth :day 0)) (holidayToDateEsp %1))
  
  "año pasad[ao] en <special occasion>"
  [#"(?i)a[nñ]o (pasad[ao]|anterior) en"
  #"(?i)((d[ií]a del? )?na[bv]idad|noche\s?[bv]uena|noche\s?[bv]ieja|(d[ií]a del? )?a[nñ]o nue[bv]o|(d[ií]a del? )san valent[ií]n|(d[ií]a del? )amor y la amistad|(d[ií]a del? )(la )?constitu[sc]i[oó]n|(d[ií]a del? )(la )?independen[sc]ia|(d[ií]a del? )?[hj]all?owe?en|(d[ií]a del? )(las? )?madres?|(d[ií]a del? )(los? )?muertos|(d[ií]a del? )(la )?re[vb]olu[sc]i[oó]n|(d[ií]a del? )(la )?[vb]irgen( de guadalupe)?)"]
  (intersect (cycle-nth-after :year -1 (cycle-nth :day 0)) (holidayToDateEsp %2))
  
  ;xmas del proximo año
  "<special occasion> del proximo año"
  [#"(?i)((d[ií]a del? )?na[bv]idad|noche\s?[bv]uena|noche\s?[bv]ieja|(d[ií]a del? )?a[nñ]o nue[bv]o|(d[ií]a del? )san valent[ií]n|(d[ií]a del? )amor y la amistad|(d[ií]a del? )(la )?constitu[sc]i[oó]n|(d[ií]a del? )(la )?independen[sc]ia|(d[ií]a del? )?[hj]all?owe?en|(d[ií]a del? )(las? )?madres?|(d[ií]a del? )(los? )?muertos|(d[ií]a del? )(la )?re[vb]olu[sc]i[oó]n|(d[ií]a del? )(la )?[vb]irgen( de guadalupe)?)"
  #"(?i)(del|de el) a[nñ]o (que sigue|que viene|siguiente)"] ; sequence of two tokens with a time fn
  (intersect (cycle-nth-after :year 1 (cycle-nth :day 0)) (holidayToDateEsp %1))
  
  ;xmas del proximo año
  "<special occasion> del proximo año"
  [#"(?i)((d[ií]a del? )?na[bv]idad|noche\s?[bv]uena|noche\s?[bv]ieja|(d[ií]a del? )?a[nñ]o nue[bv]o|(d[ií]a del? )san valent[ií]n|(d[ií]a del? )amor y la amistad|(d[ií]a del? )(la )?constitu[sc]i[oó]n|(d[ií]a del? )(la )?independen[sc]ia|(d[ií]a del? )?[hj]all?owe?en|(d[ií]a del? )(las? )?madres?|(d[ií]a del? )(los? )?muertos|(d[ií]a del? )(la )?re[vb]olu[sc]i[oó]n|(d[ií]a del? )(la )?[vb]irgen( de guadalupe)?)"
  #"(?i)(de el|del) (pr[o|ó]ximo|siguiente) a[nñ]o"] ; sequence of two tokens with a time fn
  (intersect (cycle-nth-after :year 1 (cycle-nth :day 0)) (holidayToDateEsp %1))
  
  ;next years xmas
  "año siguiente en <special occasion>"
  [#"(?i)a[nñ]o (que sigue|que viene|siguiente|pr[o|ó]xim[o|a]) en"
  #"(?i)((d[ií]a del? )?na[bv]idad|noche\s?[bv]uena|noche\s?[bv]ieja|(d[ií]a del? )?a[nñ]o nue[bv]o|(d[ií]a del? )san valent[ií]n|(d[ií]a del? )amor y la amistad|(d[ií]a del? )(la )?constitu[sc]i[oó]n|(d[ií]a del? )(la )?independen[sc]ia|(d[ií]a del? )?[hj]all?owe?en|(d[ií]a del? )(las? )?madres?|(d[ií]a del? )(los? )?muertos|(d[ií]a del? )(la )?re[vb]olu[sc]i[oó]n|(d[ií]a del? )(la )?[vb]irgen( de guadalupe)?)"]
  (intersect (cycle-nth-after :year 1 (cycle-nth :day 0)) (holidayToDateEsp %2))
  
  ;next years xmas
  "año siguiente en <special occasion>"
  [#"(?i)(pr[oó]ximo|siguiente) a[nñ]o en"
  #"(?i)((d[ií]a del? )?na[bv]idad|noche\s?[bv]uena|noche\s?[bv]ieja|(d[ií]a del? )?a[nñ]o nue[bv]o|(d[ií]a del? )san valent[ií]n|(d[ií]a del? )amor y la amistad|(d[ií]a del? )(la )?constitu[sc]i[oó]n|(d[ií]a del? )(la )?independen[sc]ia|(d[ií]a del? )?[hj]all?owe?en|(d[ií]a del? )(las? )?madres?|(d[ií]a del? )(los? )?muertos|(d[ií]a del? )(la )?re[vb]olu[sc]i[oó]n|(d[ií]a del? )(la )?[vb]irgen( de guadalupe)?)"]
  (intersect (cycle-nth-after :year 1 (cycle-nth :day 0)) (holidayToDateEsp %2))
  
  ;xmas of this year
  "<special occasion> del año en curso"
  [#"(?i)((d[ií]a del? )?na[bv]idad|noche\s?[bv]uena|noche\s?[bv]ieja|(d[ií]a del? )?a[nñ]o nue[bv]o|(d[ií]a del? )san valent[ií]n|(d[ií]a del? )amor y la amistad|(d[ií]a del? )(la )?constitu[sc]i[oó]n|(d[ií]a del? )(la )?independen[sc]ia|(d[ií]a del? )?[hj]all?owe?en|(d[ií]a del? )(las? )?madres?|(d[ií]a del? )(los? )?muertos|(d[ií]a del? )(la )?re[vb]olu[sc]i[oó]n|(d[ií]a del? )(la )?[vb]irgen( de guadalupe)?)"
  #"(?i)(del|de el) a[nñ]o (actual|en (curso|progreso))"] ; sequence of two tokens with a time fn
  (intersect (cycle-nth-after :year 0 (cycle-nth :day 0)) (holidayToDateEsp %1))
  
  ;xmas of this year
  "<special occasion> del año en curso"
  [#"(?i)((d[ií]a del? )?na[bv]idad|noche\s?[bv]uena|noche\s?[bv]ieja|(d[ií]a del? )?a[nñ]o nue[bv]o|(d[ií]a del? )san valent[ií]n|(d[ií]a del? )amor y la amistad|(d[ií]a del? )(la )?constitu[sc]i[oó]n|(d[ií]a del? )(la )?independen[sc]ia|(d[ií]a del? )?[hj]all?owe?en|(d[ií]a del? )(las? )?madres?|(d[ií]a del? )(los? )?muertos|(d[ií]a del? )(la )?re[vb]olu[sc]i[oó]n|(d[ií]a del? )(la )?[vb]irgen( de guadalupe)?)"
  #"(?i)(del actual|de este) a[nñ]o (actual|en (curso|progreso))?"] ; sequence of two tokens with a time fn
  (intersect (cycle-nth-after :year 0 (cycle-nth :day 0)) (holidayToDateEsp %1))
  
  ;this year's xmas
  "este año en <special occasion>"
  [#"(?i)(actual|este) a[nñ]o( actual| en (curso|progreso))? en"
  #"(?i)((d[ií]a del? )?na[bv]idad|noche\s?[bv]uena|noche\s?[bv]ieja|(d[ií]a del? )?a[nñ]o nue[bv]o|(d[ií]a del? )san valent[ií]n|(d[ií]a del? )amor y la amistad|(d[ií]a del? )(la )?constitu[sc]i[oó]n|(d[ií]a del? )(la )?independen[sc]ia|(d[ií]a del? )?[hj]all?owe?en|(d[ií]a del? )(las? )?madres?|(d[ií]a del? )(los? )?muertos|(d[ií]a del? )(la )?re[vb]olu[sc]i[oó]n|(d[ií]a del? )(la )?[vb]irgen( de guadalupe)?)"]
  (intersect (cycle-nth-after :year 0 (cycle-nth :day 0)) (holidayToDateEsp %2))
  
  ;this year's xmas
  "este año en <special occasion>"
  [#"(?i)el a[nñ]o( actual| en (curso|progreso)) en"
  #"(?i)((d[ií]a del? )?na[bv]idad|noche\s?[bv]uena|noche\s?[bv]ieja|(d[ií]a del? )?a[nñ]o nue[bv]o|(d[ií]a del? )san valent[ií]n|(d[ií]a del? )amor y la amistad|(d[ií]a del? )(la )?constitu[sc]i[oó]n|(d[ií]a del? )(la )?independen[sc]ia|(d[ií]a del? )?[hj]all?owe?en|(d[ií]a del? )(las? )?madres?|(d[ií]a del? )(los? )?muertos|(d[ií]a del? )(la )?re[vb]olu[sc]i[oó]n|(d[ií]a del? )(la )?[vb]irgen( de guadalupe)?)"]
  (intersect (cycle-nth-after :year 0 (cycle-nth :day 0)) (holidayToDateEsp %2))
  
  "<day-of-week> despues de la que sigue"
  [#"(?i)(lunes|lun?|martes|mar?|mi[eé]\.?(rcoles)?|mx|mier?|jueves|jue|jue|viernes|vier|vie|s[áa]bado|s[áa]b|domingo|dom)(\.)?"
  #"(?i)despues (del?|de el)( la)? (que )?(sigue|siguiente|pr[oó]ximo)"]
  (pred-nth-not-immediate (namedDayToNumber %1) 1)
  
  "<day-of-week> antepasada"
  [#"(?i)(lunes|lun?|martes|mar?|mi[eé]\.?(rcoles)?|mx|mier?|jueves|jue|jue|viernes|vier|vie|s[áa]bado|s[áa]b|domingo|dom)(\.)?"
  #"(?i)(ante\s?pasad[ao])"]
  (pred-nth (namedDayToNumber %1) -2)
  
  "la semana pasada en <day of the week>"
  [#"(?i)semana (pasada|anterior|que paso) en"
  #"(?i)(lunes|lun?|martes|mar?|mi[eé]\.?(rcoles)?|mx|mier?|jueves|jue|jue|viernes|vier|vie|s[áa]bado|s[áa]b|domingo|dom)(\.)?"]
  (intersect (cycle-nth-after :week -1 (cycle-nth :day 0)) (namedDayToNumber %2))

  "<day of the week> de la semana pasada"
  [#"(?i)(lunes|lun?|martes|mar?|mi[eé]\.?(rcoles)?|mx|mier?|jueves|jue|jue|viernes|vier|vie|s[áa]bado|s[áa]b|domingo|dom)(\.)?"
  #"(?i)de la semana (pasada|anterior|que paso)"] ; sequence of two tokens with a time fn
  (intersect (cycle-nth-after :week -1 (cycle-nth :day 0)) (namedDayToNumber %1))
  
  "semana siguiente en <day of the week>"
  [#"(?i)semana (que sigue|que viene|siguiente|pr[oó]xim[oa]) en"
  #"(?i)(lunes|lun?|martes|mar?|mi[eé]\.?(rcoles)?|mx|mier?|jueves|jue|jue|viernes|vier|vie|s[áa]bado|s[áa]b|domingo|dom)(\.)?"]
  (intersect (cycle-nth-after :week 1 (cycle-nth :day 0)) (namedDayToNumber %2))
  
  "siguiente semana en <day of the week>"
  [#"(?i)(pr[oó]xim[oa]|siguiente) semana en"
  #"(?i)(lunes|lun?|martes|mar?|mi[eé]\.?(rcoles)?|mx|mier?|jueves|jue|jue|viernes|vier|vie|s[áa]bado|s[áa]b|domingo|dom)(\.)?"]
  (intersect (cycle-nth-after :week 1 (cycle-nth :day 0)) (namedDayToNumber %2))

  "<day of the week> de la semana que sigue"
  [#"(?i)(lunes|lun?|martes|mar?|mi[eé]\.?(rcoles)?|mx|mier?|jueves|jue|jue|viernes|vier|vie|s[áa]bado|s[áa]b|domingo|dom)(\.)?"
  #"(?i)de la semana (que sigue|que viene|siguiente|pr[oó]xim[oa])"] ; sequence of two tokens with a time fn
  (intersect (cycle-nth-after :week 1 (cycle-nth :day 0)) (namedDayToNumber %1))
  
  "<day of the week> de la semana que sigue"
  [#"(?i)(lunes|lun?|martes|mar?|mi[eé]\.?(rcoles)?|mx|mier?|jueves|jue|jue|viernes|vier|vie|s[áa]bado|s[áa]b|domingo|dom)(\.)?"
  #"(?i)de la (siguiente|pr[oó]xim[oa]) semana"] ; sequence of two tokens with a time fn
  (intersect (cycle-nth-after :week 1 (cycle-nth :day 0)) (namedDayToNumber %1))
  
  "esta semana en <day of the week>"
  [#"(?i)(actual|esta) semana( actual| en (curso|progreso))? en"
  #"(?i)(lunes|lun?|martes|mar?|mi[eé]\.?(rcoles)?|mx|mier?|jueves|jue|jue|viernes|vier|vie|s[áa]bado|s[áa]b|domingo|dom)(\.)?"]
  (intersect (cycle-nth-after :week 0 (cycle-nth :day 0)) (namedDayToNumber %2))
  
  "esta semana en <day of the week>"
  [#"(?i)la semana( actual| en (curso|progreso)) en"
  #"(?i)(lunes|lun?|martes|mar?|mi[eé]\.?(rcoles)?|mx|mier?|jueves|jue|jue|viernes|vier|vie|s[áa]bado|s[áa]b|domingo|dom)(\.)?"]
  (intersect (cycle-nth-after :week 0 (cycle-nth :day 0)) (namedDayToNumber %2))
  
  "<day of the week> de esta semana"
  [#"(?i)(lunes|lun?|martes|mar?|mi[eé]\.?(rcoles)?|mx|mier?|jueves|jue|jue|viernes|vier|vie|s[áa]bado|s[áa]b|domingo|dom)(\.)?"
  #"(?i)de (la actual|esta) semana"] ; sequence of two tokens with a time fn
  (intersect (cycle-nth-after :week 0 (cycle-nth :day 0)) (namedDayToNumber %1))
  
  "<day of the week> de esta semana"
  [#"(?i)(lunes|lun?|martes|mar?|mi[eé]\.?(rcoles)?|mx|mier?|jueves|jue|jue|viernes|vier|vie|s[áa]bado|s[áa]b|domingo|dom)(\.)?"
  #"(?i)de la semana( actual| en (curso|progreso))"] ; sequence of two tokens with a time fn
  (intersect (cycle-nth-after :week 0 (cycle-nth :day 0)) (namedDayToNumber %1))

  "<non ordinal> del proximo mes"
  [(integer 1 31)
  #"(?i)(de el|del?) (pr[oó]ximo|siguiente) mes"]
  (intersect (cycle-nth-after :month 1 (cycle-nth :day 0)) (day-of-month (:value %1)))
  
  "<non ordinal> del proximo mes"
  [(integer 1 31)
  #"(?i)(de el|del?) mes (que sigue|que viene|siguiente|pr[oó]ximo)"]
  (intersect (cycle-nth-after :month 1 (cycle-nth :day 0)) (day-of-month (:value %1)))
  
  "el proximo mes el <non ordinal>"
  [#"(?i)(el )?mes (que sigue|que viene|siguiente|pr[oó]ximo) el"
  (integer 1 31)]
  (intersect (cycle-nth-after :month 1 (cycle-nth :day 0)) (day-of-month (:value %2)))
  
  "el proximo mes el <non ordinal>"
  [#"(?i)(el )?(siguiente|pr[oó]ximo) mes el"
  (integer 1 31)]
  (intersect (cycle-nth-after :month 1 (cycle-nth :day 0)) (day-of-month (:value %2)))
  
  "<non ordinal> del mes pasad[ao]"
  [(integer 1 31)
  #"(?i)(de el|del?) mes (pasad[ao]|anterior|que paso)"]
  (intersect (cycle-nth-after :month -1 (cycle-nth :day 0)) (day-of-month (:value %1)))
  
  "<non ordinal> del pasad[ao] mes"
  [(integer 1 31)
  #"(?i)(de el|del?) (pasad[ao]|anterior) mes"]
  (intersect (cycle-nth-after :month -1 (cycle-nth :day 0)) (day-of-month (:value %1)))
  
  "el pasad[ao] mes el <non ordinal>"
  [#"(?i)(el )?(pasad[ao]|anterior|ultimo) mes( en)? el"
  (integer 1 31)]
  (intersect (cycle-nth-after :month -1 (cycle-nth :day 0)) (day-of-month (:value %2)))
  
  "el mes pasad[ao] el <non ordinal>"
  [#"(?i)(el )?mes (pasad[ao]|anterior|que paso|ultimo)( en)? el"
  (integer 1 31)]
  (intersect (cycle-nth-after :month -1 (cycle-nth :day 0)) (day-of-month (:value %2)))
  
  "<non ordinal> de este mes"
  [(integer 1 31)
  #"(?i)del? (actual|este) mes"]
  (intersect (cycle-nth-after :month 0 (cycle-nth :day 0)) (day-of-month (:value %1)))
  
  "<non ordinal> del mes actual"
  [(integer 1 31)
  #"(?i)(de el|del) mes (actual| en (curso|progreso))"]
  (intersect (cycle-nth-after :month 0 (cycle-nth :day 0)) (day-of-month (:value %1)))
  
  "este mes en el <ordinal>"
  [#"(?i)este mes( en)? el"
  (integer 1 31)]
  (intersect (cycle-nth-after :month 0 (cycle-nth :day 0)) (day-of-month (:value %2)))
  
  "el mes actual en el <non ordinal>"
  [#"(?i)(el )?mes (actual| en (curso|progreso))( en)? el?"
  (integer 1 31)]
  (intersect (cycle-nth-after :month 0 (cycle-nth :day 0)) (day-of-month (:value %2)))
  
  "ultimo dia de <month> de <year>"
  [#"(?i)ultimo (d[ií]a )?(((del?|de el) mes de)|de)?"
  #"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"
  #"(?i)(del?|de el|para( el)?)"
  (integer 1000 2100)]
  (cycle-nth-after :day -1 (cycle-nth-after :month 1 (intersect (year (:value %4)) (namedMonthToNumber %2))))
  
  "ultimo dia de <month> <year>"
  [#"(?i)ultimo (d[ií]a )?(((del?|de el) mes de)|de)?"
  #"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"
  (integer 1000 2100)]
  (cycle-nth-after :day -1 (cycle-nth-after :month 1 (intersect (year (:value %3)) (namedMonthToNumber %2))))
  
  "ultimo dia de <month> de este año"
  [#"(?i)ultimo (d[ií]a )?(((del?|de el) mes de)|de)?"
  #"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov\.?"
  #"(?i)(del?|de el|para( el)?) (actual|este) a[ñn]o"]
  (intersect (cycle-nth :year 0) (cycle-nth-after :day -1 (cycle-nth-after :month 1 (namedMonthToNumber %2))))
  
  "ultimo dia de <month> de este año"
  [#"(?i)ultimo (d[ií]a )?(((del?|de el) mes de)|de)?"
  #"(?i)diciembre|dic\.?"
  #"(?i)(del?|de el|para( el)?) (actual|este) a[ñn]o"]
  (intersect (cycle-nth :year 0) (month-day 12 31))
  
  "ultimo dia de <month> de este año"
  [#"(?i)ultimo (d[ií]a )?(((del?|de el) mes de)|de)?"
  #"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov\.?"
  #"(?i)(del?|de el|para( el)?) a[ñn]o (actual|en (curso|progreso))"]
  (intersect (cycle-nth :year 0) (cycle-nth-after :day -1 (cycle-nth-after :month 1 (namedMonthToNumber %2))))
  
  "ultimo dia de <month> de este año"
  [#"(?i)ultimo (d[ií]a )?(((del?|de el) mes de)|de)?"
  #"(?i)diciembre|dic\.?"
  #"(?i)(del?|de el|para( el)?) a[ñn]o (actual|en (curso|progreso))"]
  (intersect (cycle-nth :year 0) (month-day 12 31))

  "last day of this month"
  [#"(?i)ultimo (d[ií]a )?(del? |de el )(este|actual) mes"]
  (cycle-nth-after :day -1 (cycle-nth-after :month 1 (cycle-nth :day 0)))
  
  "last day of this month"
  [#"(?i)ultimo (d[ií]a )?(del? |de el )?mes (actual| en (curso|progreso))"]
  (cycle-nth-after :day -1 (cycle-nth-after :month 1 (cycle-nth :day 0)))
  
  "last day of next month"
  [#"(?i)ultimo (d[ií]a )?(del? |de el )?mes (que sigue|que viene|siguiente|pr[oó]ximo)"]
  (cycle-nth-after :day -1 (cycle-nth-after :month 2 (cycle-nth :day 0)))
  
  "last day of next month"
  [#"(?i)ultimo (d[ií]a )?(del? |de el )?(siguiente|pr[oó]ximo) mes"]
  (cycle-nth-after :day -1 (cycle-nth-after :month 2 (cycle-nth :day 0)))
  
  "last day of last month"
  [#"(?i)ultimo (d[ií]a )?(del? |de el )?mes (pasad[ao]|anterior|que paso)"]
  (cycle-nth-after :day -1 (cycle-nth-after :month 0 (cycle-nth :day 0)))
  
  "last day of last month"
  [#"(?i)ultimo (d[ií]a )?(del? |de el )?(pasad[ao]|anterior) mes"]
  (cycle-nth-after :day -1 (cycle-nth-after :month 0 (cycle-nth :day 0)))
  
  "primer dia of <month> of <year>"
  [#"(?i)primero? (d[ií]a )?(((del|de el) mes de)|de)?"
  #"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"
  #"(?i)(del?|de el)"
  (integer 1000 2100)]
  (intersect (year (:value %4)) (namedMonthToNumber %2))
  
  "<ordinal> <named-day> del <month> del <year>"
  [(dim :ordinal #(<= 1 (:value %) 4))
  #"(?i)(lunes|lun?|martes|mar?|mi[eé]\.?(rcoles)?|mx|mier?|jueves|jue|jue|viernes|vier|vie|s[áa]bado|s[áa]b|domingo|dom)(\.)?"
  #"(?i)(del?|de el)( mes de)?"
  #"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"
  #"(?i)(del?|de el)"
  (integer 1000 2100)]
  (intersect (year (:value %6)) (namedMonthToNumber %4) (cycle-nth-after :week (:value %1) (cycle-nth :day 0)) (namedDayToNumber %2))
  
  "<ordinal> <named-day> del <month> <year>"
  [(dim :ordinal #(<= 1 (:value %) 4))
  #"(?i)(lunes|lun?|martes|mar?|mi[eé]\.?(rcoles)?|mx|mier?|jueves|jue|jue|viernes|vier|vie|s[áa]bado|s[áa]b|domingo|dom)(\.)?"
  #"(?i)(del?|de el)( mes de)?"
  #"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"
  (integer 1000 2100)]
  (intersect (year (:value %5)) (namedMonthToNumber %4) (cycle-nth-after :week (:value %1) (cycle-nth :day 0)) (namedDayToNumber %2))

  "<ordinal> <named-day> del <month> del año actual"
  [(dim :ordinal #(<= 1 (:value %) 4))
  #"(?i)(lunes|lun?|martes|mar?|mi[eé]\.?(rcoles)?|mx|mier?|jueves|jue|jue|viernes|vier|vie|s[áa]bado|s[áa]b|domingo|dom)(\.)?"
  #"(?i)(de(\s?e)?l?)( mes de)?"
  #"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"]
  (intersect (:year 0) (namedMonthToNumber %4) (cycle-nth-after :week (:value %1) (cycle-nth :day 0)) (namedDayToNumber %2))

  "siguiente semana"
  [#"(?i)(siguiente|pr[o|ó]xima) semana"]
  (cycle-nth-after :week 1 (cycle-nth :day 0))

  "este mes"
  [#"(?i)(este|actual) mes"]
  (cycle-nth-after :month 0 (cycle-nth :day 0))

  "mes actual"
  [#"(?i)mes (este|actual)"]
  (cycle-nth-after :month 0 (cycle-nth :day 0))

  "primer dia of this month"
  [#"(?i)primero? (d[ií]a )?del? (este|actual) mes"]
  (cycle-nth-after :month 0 (cycle-nth :day 0))
  
  "primer dia of this month"
  [#"(?i)primero? (d[ií]a )?del? mes (actual| en (curso|progreso))"]
  (cycle-nth-after :month 0 (cycle-nth :day 0))
  
  "primer dia of next month"
  [#"(?i)primero? (d[ií]a )?(del|de el) mes (que sigue|que viene|siguiente|pr[oó]ximo)"]
  (cycle-nth-after :month 1 (cycle-nth :day 0))
  
  "primer dia of next month"
  [#"(?i)primero? (d[ií]a )?(del|de el) (siguiente|pr[oó]ximo) mes"]
  (cycle-nth-after :month 1 (cycle-nth :day 0))
  
  "primer dia of last month"
  [#"(?i)primero? (d[ií]a )?(del|de el) mes (pasad[ao]|anterior|que paso)"]
  (cycle-nth-after :month -1 (cycle-nth :day 0))
  
  "primer dia of last month"
  [#"(?i)primero? (d[ií]a )?(del|de el) (pasad[ao]|anterior) mes"]
  (cycle-nth-after :month -1 (cycle-nth :day 0))
  
  "primer <named day> del mes proximo"
  [#"(?i)primero?"
  #"(?i)(lunes|lun?|martes|mar?|mi[eé]\.?(rcoles)?|mx|mier?|jueves|jue|jue|viernes|vier|vie|s[áa]bado|s[áa]b|domingo|dom)(\.)?"
  #"(?i)(del|de el) mes (que sigue|que viene|siguiente|pr[oó]ximo)"]
  (intersect (cycle-nth-after :month 1 (cycle-nth :day 0)) (cycle-nth-after :day 0 (namedDayToNumber %2)))
  
  "primer <named day> del proximo mes"
  [#"(?i)primero?"
  #"(?i)(lunes|lun?|martes|mar?|mi[eé]\.?(rcoles)?|mx|mier?|jueves|jue|jue|viernes|vier|vie|s[áa]bado|s[áa]b|domingo|dom)(\.)?"
  #"(?i)(del|de el) (siguiente|pr[oó]ximo) mes"]
  (intersect (cycle-nth-after :month 1 (cycle-nth :day 0)) (cycle-nth-after :day 0 (namedDayToNumber %2)))
  
  "primer <named day> del mes actual"
  [#"(?i)primero?"
  #"(?i)(lunes|lun?|martes|mar?|mi[eé]\.?(rcoles)?|mx|mier?|jueves|jue|jue|viernes|vier|vie|s[áa]bado|s[áa]b|domingo|dom)(\.)?"
  #"(?i)del? mes (actual| en (curso|progreso))"]
  (intersect (cycle-nth-after :month 0 (cycle-nth :day 0)) (cycle-nth-after :day 0 (namedDayToNumber %2)))
  
  "primer <named day> de este"
  [#"(?i)primero?"
  #"(?i)(lunes|lun?|martes|mar?|mi[eé]\.?(rcoles)?|mx|mier?|jueves|jue|jue|viernes|vier|vie|s[áa]bado|s[áa]b|domingo|dom)(\.)?"
  #"(?i)del? (este|actual) mes"]
  (intersect (cycle-nth-after :month 0 (cycle-nth :day 0)) (cycle-nth-after :day 0 (namedDayToNumber %2)))
  
  "primer <named day> del mes"
  [#"(?i)primero?"
  #"(?i)(lunes|lun?|martes|mar?|mi[eé]\.?(rcoles)?|mx|mier?|jueves|jue|jue|viernes|vier|vie|s[áa]bado|s[áa]b|domingo|dom)(\.)?"
  #"(?i)del? mes"]
  (intersect (cycle-nth-after :month 0 (cycle-nth :day 0)) (cycle-nth-after :day 0 (namedDayToNumber %2)))
  ;;
  ;; This, Next, Last

  ;; assumed to be strictly in the future:
  ;; "this Monday" => next week if today is Monday
  "el <day-of-week>"
  [#"(?i)(este|el) (d(i|í)a)?"
  #"(?i)(lunes|lun?|martes|mar?|mi[eé]\.?(rcoles)?|mx|mier?|jueves|jue|jue|viernes|vier|vie|s[áa]bado|s[áa]b|domingo|dom)(\.)?"
  #"(?i)(anterior|pasado) ?"]
  (pred-nth-not-immediate (namedDayToNumber %2) -1)

  "este <day-of-week>"
  [#"(?i)(este|pr[oó]ximo|siguiente|el) (d(i|í)a)?"
  #"(?i)(lunes|lun?|martes|mar?|mi[eé]\.?(rcoles)?|mx|mier?|jueves|jue|jue|viernes|vier|vie|s[áa]bado|s[áa]b|domingo|dom)(\.)?"]
  (pred-nth-not-immediate (namedDayToNumber %2) 0)
  
  "este <day-of-week>"
  [#"(?i)(lunes|lun?|martes|mar?|mi[eé]\.?(rcoles)?|mx|mier?|jueves|jue|jue|viernes|vier|vie|s[áa]bado|s[áa]b|domingo|dom)(\.)?"
  #"(?i)que (vienen?|sigue)"]
  (pred-nth-not-immediate (namedDayToNumber %1) 0)
  
  "este <named-month> <day-of-month> (non ordinal)" ;
  [#"(?i)(este|pr[oó]ximo|siguiente)"
   #"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"
  (integer 1 31)]
  (intersect (namedMonthToNumber %2) (day-of-month (:value %3)))
  
  "<named-month> <day-of-month> que viene" ;
  [#"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"
  (integer 1 31)
  #"(?i)que (vienen?|sigue)"]
  (intersect (namedMonthToNumber %1) (day-of-month (:value %2)))
  
  "este <non ordinal> de <month>" ;
  [#"(?i)(este|pr[oó]xim[oa]|siguiente)"
  (integer 1 31)
   #"(?i)de"
   #"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"]
  (intersect (namedMonthToNumber %4) (day-of-month (:value %2)))
  
  "<non ordinal> de <month> que viene" ;
  [(integer 1 31)
   #"(?i)de"
   #"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"
   #"(?i)que (vienen?|sigue)"]
  (intersect (namedMonthToNumber %3) (day-of-month (:value %1)))
  
  "this|next <special occasion>"
  [#"(?i)(este|pr[oó]xim[oa]|siguiente)"
  #"(?i)((d[ií]a del? )?na[bv]idad|noche\s?[bv]uena|noche\s?[bv]ieja|(d[ií]a del? )?a[nñ]o nue[bv]o|(d[ií]a del? )san valent[ií]n|(d[ií]a del? )amor y la amistad|(d[ií]a del? )(la )?constitu[sc]i[oó]n|(d[ií]a del? )(la )?independen[sc]ia|(d[ií]a del? )?[hj]all?owe?en|(d[ií]a del? )(las? )?madres?|(d[ií]a del? )(los? )?muertos|(d[ií]a del? )(la )?re[vb]olu[sc]i[oó]n|(d[ií]a del? )(la )?[vb]irgen( de guadalupe)?)"]
  (pred-nth-not-immediate (holidayToDateEsp %2) 0)
  
  "this|next <special occasion>"
  [#"(?i)((d[ií]a del? )?na[bv]idad|noche\s?[bv]uena|noche\s?[bv]ieja|(d[ií]a del? )?a[nñ]o nue[bv]o|(d[ií]a del? )san valent[ií]n|(d[ií]a del? )amor y la amistad|(d[ií]a del? )(la )?constitu[sc]i[oó]n|(d[ií]a del? )(la )?independen[sc]ia|(d[ií]a del? )?[hj]all?owe?en|(d[ií]a del? )(las? )?madres?|(d[ií]a del? )(los? )?muertos|(d[ií]a del? )(la )?re[vb]olu[sc]i[oó]n|(d[ií]a del? )(la )?[vb]irgen( de guadalupe)?)"
  #"(?i)que (vienen?|sigue)"]
  (pred-nth-not-immediate (holidayToDateEsp %1) 0)
  
  "last <day-of-week>"
  [#"(?i)(pasad[ao]|ultimo|anterior)"
  #"(?i)(lunes|lun?|martes|mar?|mi[eé]\.?(rcoles)?|mx|mier?|jueves|jue|jue|viernes|vier|vie|s[áa]bado|s[áa]b|domingo|dom)(\.)?"]
  (pred-nth (namedDayToNumber %2) -1)
  
  "last <day-of-week>"
  [#"(?i)(lunes|lun?|martes|mar?|mi[eé]\.?(rcoles)?|mx|mier?|jueves|jue|jue|viernes|vier|vie|s[áa]bado|s[áa]b|domingo|dom)(\.)?"
  #"(?i)(pasad[ao]|ultimo|anterior|que paso)"]
  (pred-nth (namedDayToNumber %1) -1)
  
  "this past|last <named-month> <day-of-month> (non ordinal)" ;
  [#"(?i)(pasad[ao]|ultimo|anterior)"
  #"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"
  (integer 1 31)]
  (pred-nth (intersect (namedMonthToNumber %2) (day-of-month (:value %3))) -1)
  
  "this past|last <named-month> <day-of-month> (non ordinal)" ;
  [#"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"
  (integer 1 31)
  #"(?i)(pasad[ao]|ultimo|anterior|que paso)"]
  (pred-nth (intersect (namedMonthToNumber %1) (day-of-month (:value %2))) -1)
  
  "this past|last <day-of-month> (non ordinal) of <named-month>"
  [#"(?i)(pasad[ao]|ultimo|anterior)"
  (integer 1 31)
  #"(?i)de"
  #"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"]
  (pred-nth (intersect (namedMonthToNumber %4) (day-of-month (:value %2))) -1)
  
  "this past|last <day-of-month> (non ordinal) of <named-month>"
  [(integer 1 31)
  #"(?i)de"
  #"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"
  #"(?i)(pasad[ao]|ultimo|anterior|que paso)"]
  (pred-nth (intersect (namedMonthToNumber %3) (day-of-month (:value %1))) -1)
  
  "this past|last <day-of-month> (non ordinal) <named-month>" ;
  [#"(?i)(pasad[ao]|ultimo|anterior)"
  (integer 1 31)
  #"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"]
  (pred-nth (intersect (namedMonthToNumber %3) (day-of-month (:value %2))) -1)
  
  "this past|last <day-of-month> (non ordinal) <named-month>" ;
  [(integer 1 31)
  #"(?i)enero|ene|febrero|feb|marzo|mar|abril|abr|mayo|junio|jun|julio|jul|agosto|ago|septiembre|sept|octubre|oct|noviembre|nov|diciembre|dic\.?"
  #"(?i)(pasad[ao]|ultimo|anterior|que paso)"]
  (pred-nth (intersect (namedMonthToNumber %2) (day-of-month (:value %1))) -1)
  
  "last <special occasion>"
  [#"(?i)(pasad[oa]|ultimo|anterior)"
  #"(?i)((d[ií]a del? )?na[bv]idad|noche\s?[bv]uena|noche\s?[bv]ieja|(d[ií]a del? )?a[nñ]o nue[bv]o|(d[ií]a del? )san valent[ií]n|(d[ií]a del? )amor y la amistad|(d[ií]a del? )(la )?constitu[sc]i[oó]n|(d[ií]a del? )(la )?independen[sc]ia|(d[ií]a del? )?[hj]all?owe?en|(d[ií]a del? )(las? )?madres?|(d[ií]a del? )(los? )?muertos|(d[ií]a del? )(la )?re[vb]olu[sc]i[oó]n|(d[ií]a del? )(la )?[vb]irgen( de guadalupe)?)"]
  (pred-nth (holidayToDateEsp %2) -1)
  
  "last <special occasion>"
  [#"(?i)((d[ií]a del? )?na[bv]idad|noche\s?[bv]uena|noche\s?[bv]ieja|(d[ií]a del? )?a[nñ]o nue[bv]o|(d[ií]a del? )san valent[ií]n|(d[ií]a del? )amor y la amistad|(d[ií]a del? )(la )?constitu[sc]i[oó]n|(d[ií]a del? )(la )?independen[sc]ia|(d[ií]a del? )?[hj]all?owe?en|(d[ií]a del? )(las? )?madres?|(d[ií]a del? )(los? )?muertos|(d[ií]a del? )(la )?re[vb]olu[sc]i[oó]n|(d[ií]a del? )(la )?[vb]irgen( de guadalupe)?)"
  #"(?i)(pasad[ao]|ultimo|anterior|que paso)"]
  (pred-nth (holidayToDateEsp %1) -1)
  
  "<special occasion> before last"
  [#"(?i)((d[ií]a del? )?na[bv]idad|noche\s?[bv]uena|noche\s?[bv]ieja|(d[ií]a del? )?a[nñ]o nue[bv]o|(d[ií]a del? )san valent[ií]n|(d[ií]a del? )amor y la amistad|(d[ií]a del? )(la )?constitu[sc]i[oó]n|(d[ií]a del? )(la )?independen[sc]ia|(d[ií]a del? )?[hj]all?owe?en|(d[ií]a del? )(las? )?madres?|(d[ií]a del? )(los? )?muertos|(d[ií]a del? )(la )?re[vb]olu[sc]i[oó]n|(d[ií]a del? )(la )?[vb]irgen( de guadalupe)?)"
  #"(?i)(ante\s?pasad[a|o])"]
  (pred-nth (holidayToDateEsp %1) -2)
)
