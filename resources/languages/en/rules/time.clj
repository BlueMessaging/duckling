(
  ;; generic

  "<day-of-month> <named-month> <year>" ; 4 july 81
  [(integer 1 31) 
   #"(?i)january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sept|october|oct|november|nov|december|dec\.?"
   (integer -10000 999)]
  (intersect (assoc (year (:value %3)) :latent true) (namedMonthToNumber %2) (day-of-month (:value %1)))
 
  "<named-month> <day-of-month> <year>" ; july 04 81
  [#"(?i)january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sept|october|oct|november|nov|december|dec\.?"
   (integer 1 31)
   (integer -10000 999)]
  (intersect (assoc (year (:value %3)) :latent true) (namedMonthToNumber %1) (day-of-month (:value %2)))
  
  "<named-month> <day-of-month> (ordinal)" ; march 12th 89
  [#"(?i)january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sept|october|oct|november|nov|december|dec\.?"
  (dim :ordinal #(<= 1 (:value %) 31))
  (integer 1000 2100)]
  (intersect (assoc (year (:value %3)) :latent true) (namedMonthToNumber %1) (day-of-month (:value %2)))
  
  "<named-month> <day-of-month> <year>" ; july 04 1981
  [#"(?i)january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sept|october|oct|november|nov|december|dec\.?" 
     (integer 1 31)
     (integer 1000 2100)]
  (intersect (year (:value %3)) (namedMonthToNumber %1) (day-of-month (:value %2)))
  
  "<day-of-month> <named-month> <year>" ; 04 july 1981
  [(integer 1 31)
  #"(?i)january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sept|october|oct|november|nov|december|dec\.?"
  (integer 1000 2100)]
  (intersect (year (:value %3)) (namedMonthToNumber %2) (day-of-month (:value %1)))
  
  "<named-month> <day-of-month> (ordinal)" ; march 12th 1989
  [#"(?i)january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sept|october|oct|november|nov|december|dec\.?"
  (dim :ordinal #(<= 1 (:value %) 31))
  (integer 1000 2100)]
  (intersect (year (:value %3)) (namedMonthToNumber %1) (day-of-month (:value %2)))
  
  ; same thing, with "/" in between like "february/14/1999"
  "intersect by / no latent"
  [#"(?i)january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sept|october|oct|november|nov|december|dec\.?"
  #"(?i)/|-"
  (integer 1 31)
  #"(?i)/|-"
  (integer 1000 2100)] ; sequence of two tokens with a time fn
  (intersect (year (:value %5)) (namedMonthToNumber %1) (day-of-month (:value %3)))
  
  ; same thing, with "/" in between like "february/14/99"
  "intersect by / no latent"
  [#"(?i)january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sept|october|oct|november|nov|december|dec\.?"
  #"(?i)/|-"
  (integer 1 31)
  #"(?i)/|-"
  (integer -10000 999)] ; sequence of two tokens with a time fn
  (intersect (assoc (year (:value %5)) :latent true) (namedMonthToNumber %1) (day-of-month (:value %3)))
  
  ; same thing, with "/" in between like "14/february/1999"
  "intersect by / no latent"
  [(integer 1 31)
  #"(?i)/|-"
  #"(?i)january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sept|october|oct|november|nov|december|dec\.?"
  #"(?i)/|-"
  (integer 1000 2100)] ; sequence of two tokens with a time fn
  (intersect (year (:value %5)) (namedMonthToNumber %3) (day-of-month (:value %1)))
  
  ; same thing, with "/" in between like "14/february/99"
  "intersect by / no latent"
  [(integer 1 31)
  #"(?i)/|-"
  #"(?i)january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sept|october|oct|november|nov|december|dec\.?"
  #"(?i)/|-"
  (integer -10000 999)] ; sequence of two tokens with a time fn
  (intersect (assoc (year (:value %5)) :latent true) (namedMonthToNumber %3) (day-of-month (:value %1)))

  ; mostly for January 12, 2005
  ; this is a separate rule, because commas separate very specific tokens
  ; so we want this rule's classifier to learn this
  "intersect by \",\""
  [#"(?i)january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sept|october|oct|november|nov|december|dec\.?"
  (integer 1 31)
  #","
  (integer 1000 2100)] ; sequence of two tokens with a time fn
  (intersect (year (:value %4)) (namedMonthToNumber %1) (day-of-month (:value %2)))
  
  ; mostly for January 12, 05
  ; this is a separate rule, because commas separate very specific tokens
  ; so we want this rule's classifier to learn this
  "intersect by \",\""
  [#"(?i)january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sept|october|oct|november|nov|december|dec\.?"
  (integer 1 31)
  #","
  (integer -10000 999)] ; sequence of two tokens with a time fn
  (intersect (assoc (year (:value %4)) :latent true) (namedMonthToNumber %1) (day-of-month (:value %2)))
  
  ; mostly for march 12th, 1989
  "<named-month> <day-of-month> (ordinal)" ; march 12th, 1989
  [#"(?i)january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sept|october|oct|november|nov|december|dec\.?"
  (dim :ordinal #(<= 1 (:value %) 31))
  #","
  (integer 1000 2100)]
  (intersect (year (:value %4)) (namedMonthToNumber %1) (day-of-month (:value %2)))
  
  ; mostly for march 12th, 89
  "<named-month> <day-of-month> (ordinal)" ; march 12th, 89
  [#"(?i)january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sept|october|oct|november|nov|december|dec\.?"
  (dim :ordinal #(<= 1 (:value %) 31))
  #","
  (integer -10000 999)]
  (intersect (assoc (year (:value %4)) :latent true) (namedMonthToNumber %1) (day-of-month (:value %2)))

  ; Formatted dates and times

  "mm/dd/yyyy"
  #"(0?[1-9]|1[0-2])[/-](3[01]|[12]\d|0?[1-9])[-/](\d{2,4})"
  (parse-dmy (second (:groups %1)) (first (:groups %1)) (nth (:groups %1) 2) true)

  "yyyy-mm-dd"
  #"(\d{2,4})-(0?[1-9]|1[0-2])-(3[01]|[12]\d|0?[1-9])"
  (parse-dmy (nth (:groups %1) 2) (second (:groups %1)) (first (:groups %1)) true)
  
  ;; Special occasions followed by year
  
  "christmas <year>"
  #"(?i)(xmas|christmas)( day)? (\d{2,4})"
  (parse-dmy "25" "12" (nth (:groups %1) 2) true)
  
  "christmas eve <year>"
  #"(?i)(xmas|christmas)( day)?('s)? eve (\d{2,4})"
  (parse-dmy "24" "12" (nth (:groups %1) 3) true)
  
  "new year's eve"
  #"(?i)new year'?s? eve (\d{2,4})"
  (parse-dmy "31" "12" (first (:groups %1)) true)
  
  "new year's day"
  #"(?i)new year'?s?( day)? (\d{2,4})"
  (parse-dmy "1" "1" (second (:groups %1)) true)

  "valentine's day <year>"
  #"(?i)valentine'?s?( day)? (\d{2,4})"
  (parse-dmy "14" "2" (second (:groups %1)) true)

  "memorial day <year>" ;the last Monday of May
  [#"(?i)memorial day" (integer 1000 2100)]
  (intersect (year (:value %2)) (pred-last-of (day-of-week 1) (month 5)))
  
  "memorial day <latent year>" ;the last Monday of May
  [#"(?i)memorial day" (integer -10000 999)]
  (intersect (assoc (year (:value %2)) :latent true) (pred-last-of (day-of-week 1) (month 5)))
  
  "independence day <year>"
  #"(?i)independence day (\d{2,4})"
  (parse-dmy "4" "7" (first (:groups %1)) true)
  
  "labor day <year>" ;first Monday in September
  [#"(?i)labor day" (integer 1000 2100)]
  (intersect (year (:value %2)) (month 9) (day-of-week 1))
  
  "labor day <latent year>" ;first Monday in September
  [#"(?i)labor day" (integer -10000 999)]
  (intersect (assoc (year (:value %2)) :latent true) (month 9) (day-of-week 1))

  "halloween day <year>"
  #"(?i)hall?owe?en( day)? (\d{2,4})"
  (parse-dmy "31" "10" (second (:groups %1)) true)

  ;; Relative
  
  "now"
  #"(?i)(just|right)? ?now|immediately"
  (cycle-nth :second 0)

  "today"
  #"(?i)today|(at this time)|tonight|(this morning)"
  (cycle-nth :day 0)

  "tomorrow"
  #"(?i)(tmrw?|tomm?or?row)"
  (cycle-nth :day 1)

  "yesterday"
  #"(?i)yesterday"
  (cycle-nth :day -1)
  
  "<special occasion> after next"
  [#"(?i)(xmas|christmas|new year'?s? ( eve)?|valentine'?s?|memorial|independence|labor|hall?owe?en)( day)?( eve)?"
  #"(?i)after next"]
  (pred-nth-not-immediate (holidayToDate %1) 1)
  
  "<special occasion> before last"
  [#"(?i)(xmas|christmas|new year'?s? ( eve)?|valentine'?s?|memorial|independence|labor|hall?owe?en)( day)?( eve)?"
  #"(?i)before last"]
  (pred-nth (holidayToDate %1) -2)
  
  ; xmas from last year
  "intersect by \"of\", \"from\", \"'s\""
  [#"(?i)(xmas|christmas|new year'?s? ( eve)?|valentine'?s?|memorial|independence|labor|hall?owe?en)( day)?( eve)?"
  #"(?i)(of|from|for|'s)? last year"] ; sequence of two tokens with a time fn
  (intersect (cycle-nth-after :year -1 (cycle-nth :day 0)) (holidayToDate %1))
  
  "last years <special occasion>"
  [#"(?i)last year'?s?"
  #"(?i)(xmas|christmas|new year'?s? ( eve)?|valentine'?s?|memorial|independence|labor|hall?owe?en)( day)?( eve)?"]
  (intersect (cycle-nth-after :year -1 (cycle-nth :day 0)) (holidayToDate %1))
  
  ;xmas of next year
  "<special occasion> of next year"
  [#"(?i)(xmas|christmas|new year'?s? ( eve)?|valentine'?s?|memorial|independence|labor|hall?owe?en)( day)?( eve)?"
  #"(?i)(for|of)? next year"] ; sequence of two tokens with a time fn
  (intersect (cycle-nth-after :year 1 (cycle-nth :day 0)) (holidayToDate %1))
  
  ;next years xmas
  "<special occasion> of next year"
  [#"(?i)next year'?s?"
  #"(?i)(xmas|christmas|new year'?s? ( eve)?|valentine'?s?|memorial|independence|labor|hall?owe?en)( day)?( eve)?"] ; sequence of two tokens with a time fn
  (intersect (cycle-nth-after :year 1 (cycle-nth :day 0)) (holidayToDate %2))
  
  ;xmas of this year
  "<special occasion> of next year"
  [#"(?i)(xmas|christmas|new year'?s? ( eve)?|valentine'?s?|memorial|independence|labor|hall?owe?en)( day)?( eve)?"
  #"(?i)(for|of)? this year"] ; sequence of two tokens with a time fn
  (intersect (cycle-nth-after :year 0 (cycle-nth :day 0)) (holidayToDate %1))
  
  ;this year's xmas
  "<special occasion> of next year"
  [#"(?i)this year'?s?"
  #"(?i)(xmas|christmas|new year'?s? ( eve)?|valentine'?s?|memorial|independence|labor|hall?owe?en)( day)?( eve)?"] ; sequence of two tokens with a time fn
  (intersect (cycle-nth-after :year 0 (cycle-nth :day 0)) (holidayToDate %2))
  
  "<day-of-week> after next"
  [#"(?i)monday|mon|tuesday|tues|wed?nesday|wed|thursday|thu|friday|fri|saturday|sat|sunday|sun\.?"
  #"(?i)after next"]
  (pred-nth-not-immediate (namedDayToNumber %1) 1)
  
  "<day-of-week> before last"
  [#"(?i)monday|mon|tuesday|tues|wed?nesday|wed|thursday|thu|friday|fri|saturday|sat|sunday|sun\.?"
  #"(?i)before last"]
  (pred-nth (namedDayToNumber %1) -2)
  
  "last weeks <day of the week>"
  [#"(?i)last week'?s?"
  #"(?i)monday|mon|tuesday|tues|wed?nesday|wed|thursday|thu|friday|fri|saturday|sat|sunday|sun\.?"] ; sequence of two tokens with a time fn
  (intersect (cycle-nth-after :week -1 (cycle-nth :day 0)) (namedDayToNumber %2))
  
  "<day of the week> last week"
  [#"(?i)monday|mon|tuesday|tues|wed?nesday|wed|thursday|thu|friday|fri|saturday|sat|sunday|sun\.?"
  #"(?i)(of|from|for|'s)? last week'?s?"] ; sequence of two tokens with a time fn
  (intersect (cycle-nth-after :week -1 (cycle-nth :day 0)) (namedDayToNumber %1))
  
  "next weeks <day of the week>"
  [#"(?i)next week'?s?"
  #"(?i)monday|mon|tuesday|tues|wed?nesday|wed|thursday|thu|friday|fri|saturday|sat|sunday|sun\.?"] ; sequence of two tokens with a time fn
  (intersect (cycle-nth-after :week 1 (cycle-nth :day 0)) (namedDayToNumber %2))
  
  "<day of the week> next week"
  [#"(?i)monday|mon|tuesday|tues|wed?nesday|wed|thursday|thu|friday|fri|saturday|sat|sunday|sun\.?"
  #"(?i)(of|from|for|'s)? next week'?s?"] ; sequence of two tokens with a time fn
  (intersect (cycle-nth-after :week 1 (cycle-nth :day 0)) (namedDayToNumber %1))
  
  ;sunday of next week
  "<day of the week> of next week"
  [ #"(?i)monday|mon|tuesday|tues|wed?nesday|wed|thursday|thu|friday|fri|saturday|sat|sunday|sun\.?"
  #"(?i)(for|of)? next week"] ; sequence of two tokens with a time fn
  (intersect (cycle-nth-after :week 1 (cycle-nth :day 0)) (namedDayToNumber %1))
  
  "this weeks <day of the week>"
  [#"(?i)this week'?s?"
  #"(?i)monday|mon|tuesday|tues|wed?nesday|wed|thursday|thu|friday|fri|saturday|sat|sunday|sun\.?"] ; sequence of two tokens with a time fn
  (intersect (cycle-nth-after :week 0 (cycle-nth :day 0)) (namedDayToNumber %2))
  
  "<day of the week> this week"
  [#"(?i)monday|mon|tuesday|tues|wed?nesday|wed|thursday|thu|friday|fri|saturday|sat|sunday|sun\.?"
  #"(?i)(of|from|for|'s)? this week'?s?"] ; sequence of two tokens with a time fn
  (intersect (cycle-nth-after :week 0 (cycle-nth :day 0)) (namedDayToNumber %1))
  
  "<ordinal> of next month"
  [(dim :ordinal #(<= 1 (:value %) 31))
  #"(?i)(of|from|for|'s)? next month"]
  (intersect (cycle-nth-after :month 1 (cycle-nth :day 0)) (day-of-month (:value %1)))
  
  "<non ordinal> of next month"
  [(integer 1 31)
  #"(?i)(of|from|for|'s)? next month"]
  (intersect (cycle-nth-after :month 1 (cycle-nth :day 0)) (day-of-month (:value %1)))
  
  "next month on the <ordinal>"
  [#"(?i)next month( on)?( the)?"
  (dim :ordinal #(<= 1 (:value %) 31))]
  (intersect (cycle-nth-after :month 1 (cycle-nth :day 0)) (day-of-month (:value %2)))
  
  "next month on the <non ordinal>"
  [#"(?i)next month( on)?( the)?"
  (integer 1 31)]
  (intersect (cycle-nth-after :month 1 (cycle-nth :day 0)) (day-of-month (:value %2)))
  
  "<ordinal> of last month"
  [(dim :ordinal #(<= 1 (:value %) 31))
  #"(?i)(of|from|for|'s)? last month"]
  (intersect (cycle-nth-after :month -1 (cycle-nth :day 0)) (day-of-month (:value %1)))
  
  "<non ordinal> of last month"
  [(integer 1 31)
  #"(?i)(of|from|for|'s)? last month"]
  (intersect (cycle-nth-after :month -1 (cycle-nth :day 0)) (day-of-month (:value %1)))
  
  "last month on the <ordinal>"
  [#"(?i)last month( on)?( the)?"
  (dim :ordinal #(<= 1 (:value %) 31))]
  (intersect (cycle-nth-after :month -1 (cycle-nth :day 0)) (day-of-month (:value %2)))
  
  "last month on the <non ordinal>"
  [#"(?i)last month( on)?( the)?"
  (integer 1 31)]
  (intersect (cycle-nth-after :month -1 (cycle-nth :day 0)) (day-of-month (:value %2)))
  
  "<ordinal> of this month"
  [(dim :ordinal #(<= 1 (:value %) 31))
  #"(?i)(of|from|for|'s)? this month"]
  (intersect (cycle-nth-after :month 0 (cycle-nth :day 0)) (day-of-month (:value %1)))
  
  "<non ordinal> of this month"
  [(integer 1 31)
  #"(?i)(of|from|for|'s)? this month"]
  (intersect (cycle-nth-after :month 0 (cycle-nth :day 0)) (day-of-month (:value %1)))
  
  "last month on the <ordinal>"
  [#"(?i)this month( on)?( the)?"
  (dim :ordinal #(<= 1 (:value %) 31))]
  (intersect (cycle-nth-after :month 0 (cycle-nth :day 0)) (day-of-month (:value %2)))
  
  "last month on the <non ordinal>"
  [#"(?i)this month( on)?( the)?"
  (integer 1 31)]
  (intersect (cycle-nth-after :month 0 (cycle-nth :day 0)) (day-of-month (:value %2)))
  
  ;;
  ;; This, Next, Last

  ;; assumed to be strictly in the future:
  ;; "this Monday" => next week if today is Monday
  "this|next <day-of-week>"
  [#"(?i)this|next"
  #"(?i)monday|mon|tuesday|tues|wed?nesday|wed|thursday|thu|friday|fri|saturday|sat|sunday|sun\.?"]
  (pred-nth-not-immediate (namedDayToNumber %2) 0)
  
  "this|next <named-month> <day-of-month> (non ordinal)" ; march 12
  [#"(?i)this|next"
   #"(?i)january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sept|october|oct|november|nov|december|dec\.?"
  (integer 1 31)]
  (intersect (namedMonthToNumber %2) (day-of-month (:value %3)))
  
  "this|next <named-month> <day-of-month> (ordinal)" ; march 12th
  [#"(?i)this|next"
  #"(?i)january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sept|october|oct|november|nov|december|dec\.?"
  (dim :ordinal #(<= 1 (:value %) 31))]
  (intersect (namedMonthToNumber %2) (day-of-month (:value %3)))
  
  "this|next <day-of-month> (ordinal) of <named-month>"
  [#"(?i)this|next"
  (dim :ordinal #(<= 1 (:value %) 31))
  #"(?i)of|in"
  #"(?i)january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sept|october|oct|november|nov|december|dec\.?"]
  (intersect (namedMonthToNumber %4) (day-of-month (:value %2)))

  "this|next <day-of-month> (non ordinal) of <named-month>"
  [#"(?i)this|next"
  (integer 1 31)
  #"(?i)of|in"
  #"(?i)january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sept|october|oct|november|nov|december|dec\.?"]
  (intersect (namedMonthToNumber %4) (day-of-month (:value %2)))

  "this|next <day-of-month> (non ordinal) <named-month>" ; 12 mars
  [#"(?i)this|next"
  (integer 1 31)
  #"(?i)january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sept|october|oct|november|nov|december|dec\.?"]
  (intersect (namedMonthToNumber %3) (day-of-month (:value %2)))

  "this|next <day-of-month>(ordinal) <named-month>" ; 12nd mars
  [#"(?i)this|next"
  (dim :ordinal #(<= 1 (:value %) 31))
  #"(?i)january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sept|october|oct|november|nov|december|dec\.?"]
  (intersect (namedMonthToNumber %3) (day-of-month (:value %2)))
  
  "this|next <special occasion>"
  [#"(?i)this|next"
  #"(?i)(xmas|christmas|new year'?s? ( eve)?|valentine'?s?|memorial|independence|labor|hall?owe?en)( day)?( eve)?"]
  (pred-nth-not-immediate (holidayToDate %2) 0)
  
  "last <day-of-week>"
  [#"(?i)(this past|last)"
  #"(?i)monday|mon|tuesday|tues|wed?nesday|wed|thursday|thu|friday|fri|saturday|sat|sunday|sun\.?"]
  (pred-nth (namedDayToNumber %2) -1)

  "this past|last <named-month> <day-of-month> (non ordinal)" ; march 12
  [#"(?i)(this past|last)"
   #"(?i)january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sept|october|oct|november|nov|december|dec\.?"
  (integer 1 31)]
  (pred-nth (intersect (namedMonthToNumber %2) (day-of-month (:value %3))) -1)
  
  "this past|last <named-month> <day-of-month> (ordinal)" ; march 12th
  [#"(?i)(this past|last)"
  #"(?i)january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sept|october|oct|november|nov|december|dec\.?"
  (dim :ordinal #(<= 1 (:value %) 31))]
  (pred-nth (intersect (namedMonthToNumber %2) (day-of-month (:value %3))) -1)
  
  "this past|last <day-of-month> (ordinal) of <named-month>"
  [#"(?i)(this past|last)"
  (dim :ordinal #(<= 1 (:value %) 31))
  #"(?i)of|in"
  #"(?i)january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sept|october|oct|november|nov|december|dec\.?"]
  (pred-nth (intersect (namedMonthToNumber %4) (day-of-month (:value %2))) -1)

  "this past|last <day-of-month> (non ordinal) of <named-month>"
  [#"(?i)(this past|last)"
  (integer 1 31)
  #"(?i)of|in"
  #"(?i)january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sept|october|oct|november|nov|december|dec\.?"]
  (pred-nth (intersect (namedMonthToNumber %4) (day-of-month (:value %2))) -1)

  "this past|last <day-of-month> (non ordinal) <named-month>" ; 12 mars
  [#"(?i)(this past|last)"
  (integer 1 31)
  #"(?i)january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sept|october|oct|november|nov|december|dec\.?"]
  (pred-nth (intersect (namedMonthToNumber %3) (day-of-month (:value %2))) -1)

  "this past|last <day-of-month>(ordinal) <named-month>" ; 12nd mars
  [#"(?i)(this past|last)"
  (dim :ordinal #(<= 1 (:value %) 31))
  #"(?i)january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sept|october|oct|november|nov|december|dec\.?"]
  (pred-nth (intersect (namedMonthToNumber %3) (day-of-month (:value %2))) -1)

  "last <special occasion>"
  [#"(?i)(this past|last)"
  #"(?i)(xmas|christmas|new year'?s? ( eve)?|valentine'?s?|memorial|independence|labor|hall?owe?en)( day)?( eve)?"]
  (pred-nth (holidayToDate %2) -1)
)
