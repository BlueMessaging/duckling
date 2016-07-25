(
  ; Context map
  ; Tuesday Feb 12, 2013 at 4:30am is the "now" for the tests
  {:reference-time (time/t -2 2013 2 12 4 30 0)
   :min (time/t -2 1900)
   :max (time/t -2 2100)}

  "now"
  "right now"
  "just now"
  (datetime 2013 2 12 4 30 00)

  "today"
  "at this time"
  (datetime 2013 2 12)

  "yesterday"
  (datetime 2013 2 11)

  "tomorrow"
  (datetime 2013 2 13)

  "march 3 2015"
  "march 3rd 2015"
  "march third 2015"
  "03 march 15"
  "3 march 15"
  "3/3/2015"
  "3/3/15"
  "2015-3-3"
  "2015-03-03"
  (datetime 2015 3 3 :day 3 :month 3 :year 2015)

  "10/31/1974"
  "10/31/74"
  "10-31-74"
  (datetime 1974 10 31 :day 31 :month 10 :year 1974)

  "14april 2015"
  "April 14, 2015"
  "14th April 15"
  "14 april 15"
  "14 april 2015"
  (datetime 2015 4 14 :day 14 :month 4 :years 2015)

  "friday after next"
  (datetime 2013 2 22 :day-of-week 2)

;   ;; Cycles

  "this week"
  "coming week"
  (datetime 2013 2 11 :grain :week)

  "last week"
  (datetime 2013 2 4 :grain :week)

  "next week"
  (datetime 2013 2 18 :grain :week)

  "last month"
  (datetime 2013 1)

  "next month"
  (datetime 2013 3)

  "this quarter"
  (datetime 2013 1 1 :grain :quarter)

  "next quarter"
  (datetime 2013 4 1 :grain :quarter)

  "third quarter"
  (datetime 2013 7 1 :grain :quarter)

  "last year"
  (datetime 2012)

  "this year"
  (datetime 2013)

  "next year"
  (datetime 2014)

  "sunday from last week"
  "last week's sunday"
  (datetime 2013 2 10 :day-of-week 7)

  "wednesday of next week"
  "wednesday next week"
  "wednesday after next"
  (datetime 2013 2 20 :day-of-week 3)

  "friday after next"
  (datetime 2013 2 22 :day-of-week 5)

  "monday of this week"
  (datetime 2013 2 11 :day-of-week 1)

  "tuesday of this week"
  (datetime 2013 2 12 :day-of-week 2)

  "wednesday of this week"
  (datetime 2013 2 13 :day-of-week 3)

  "the day after tomorrow"
  (datetime 2013 2 14)

  "the day before yesterday"
  (datetime 2013 2 10)


; ;; Involving periods

  "in a sec"
  "one second from now"
  (datetime 2013 2 12 4 30 1)

  "in a minute"
  "in one minute"
  (datetime 2013 2 12 4 31 0)

  "in 2 minutes"
  "in 2 more minutes"
  "2 minutes from now"
  (datetime 2013 2 12 4 32 0)

  "in 60 minutes"
  (datetime 2013 2 12 5 30 0)

  "in half an hour"
  (datetime 2013 2 12 5 0 0)

  "in 2.5 hours"
  "in 2 and an half hours"
  (datetime 2013 2 12 7 0 0)

  "in one hour"
  "in 1h"
  (datetime 2013 2 12 5 30)

  "in a couple hours"
  "in a couple of hours"
  (datetime 2013 2 12 6 30)

  "in a few hours"
  "in few hours"
  (datetime 2013 2 12 7 30)

  "in 24 hours"
  (datetime 2013 2 13 4 30)

  "in a day"
  "a day from now"
  (datetime 2013 2 13 4)

  "3 years from today"
  (datetime 2016 2)

  "in 7 days"
  (datetime 2013 2 19 4)

  "in 1 week"
  "in a week"
  (datetime 2013 2 19)

  "in about half an hour" ;; FIXME precision is lost
  (datetime 2013 2 12 5 0 0) ;; :precision "approximate"

  "7 days ago"
  (datetime 2013 2 5 4)

  "14 days ago"
  "a fortnight ago"
  (datetime 2013 1 29 4)

  "a week ago"
  "one week ago"
  "1 week ago"
  (datetime 2013 2 5)

  "three weeks ago"
  (datetime 2013 1 22)

  "three months ago"
  (datetime 2012 11 12)

  "two years ago"
  (datetime 2011 2)

  "7 days hence"
  (datetime 2013 2 19 4)

  "14 days hence"
  "a fortnight hence"
  (datetime 2013 2 26 4)

  "a week hence"
  "one week hence"
  "1 week hence"
  (datetime 2013 2 19)

  "three weeks hence"
  (datetime 2013 3 5)

  "three months hence"
  (datetime 2013 5 12)

  "two years hence"
  (datetime 2015 2)


  ; Intervals involving cycles

  "last 2 seconds"
  "last two seconds"
  (datetime-interval [2013 2 12 4 29 58] [2013 2 12 4 30 00])

  "next 3 seconds"
  "next three seconds"
  (datetime-interval [2013 2 12 4 30 01] [2013 2 12 4 30 04])

  "last 2 minutes"
  "last two minutes"
  (datetime-interval [2013 2 12 4 28] [2013 2 12 4 30])

  "next 3 minutes"
  "next three minutes"
  (datetime-interval [2013 2 12 4 31] [2013 2 12 4 34])

  "last 1 hour"
  "last one hour"
  (datetime-interval [2013 2 12 3] [2013 2 12 4])

  "next 3 hours"
  "next three hours"
  (datetime-interval [2013 2 12 5] [2013 2 12 8])

  "last 2 days"
  "last two days"
  "past 2 days"
  (datetime-interval [2013 2 10] [2013 2 12])

  "next 3 days"
  "next three days"
  (datetime-interval [2013 2 13] [2013 2 16])

  "next few days"
  (datetime-interval [2013 2 13] [2013 2 16])

  "last 2 weeks"
  "last two weeks"
  "past 2 weeks"
  (datetime-interval [2013 1 28 :grain :week] [2013 2 11 :grain :week])

  "next 3 weeks"
  "next three weeks"
  (datetime-interval [2013 2 18 :grain :week] [2013 3 11 :grain :week])

  "last 2 months"
  "last two months"
  (datetime-interval [2012 12] [2013 02])

  "next 3 months"
  "next three months"
  (datetime-interval [2013 3] [2013 6])

  "last 2 years"
  "last two years"
  (datetime-interval [2011] [2013])

  "next 3 years"
  "next three years"
  (datetime-interval [2014] [2017])
)
