(; Context map
  ; Tuesday Feb 12, 2013 at 4:30am is the "now" for the tests
  {:reference-time (time/t -2 2013 2 12 4 30)}

  "ahora"
  "ahorita"
  "cuanto antes"
  (datetime 2013 2 12 4 30 00)

  "hoy"
  (datetime 2013 2 12)

  "ayer"
  (datetime 2013 2 11)

  "anteayer"
  "antier"
  (datetime 2013 2 10)

  "mañana"
  (datetime 2013 2 13)

  "pasado mañana"
  (datetime 2013 2 14)

  "12 de diciembre del 2015"
  "12 de diciembre del 15"
  (datetime 2015 12 12 :day 12 :month 12)

  "mayo 5 del 2013" ; in part of latin america
  "5-5-2013"
  "5 de mayo de 2013"
  "5/5/2013"
  "5/5/13"
  (datetime 2013 5 5 :day 5 :month 5 :year 2013)

  "1-3-2013"
  "1/3/2013"
  (datetime 2013 3 1 :day 1 :month 3 :year 2013)

  "31/10/1974"
  "31/10/74" ; smart two-digit year resolution
  (datetime 1974 10 31 :day 31 :month 10 :year 1974)

  "miercoles de la próxima semana"
  (datetime 2013 2 20 :day-of-week 3)

  "martes de esta semana"
  (datetime 2013 2 12 :day-of-week 2)

 ;  ;; Cycles

  "esta semana"
  (datetime 2013 2 11 :grain :week)

  "la semana pasada"
  (datetime 2013 2 4 :grain :week)

  "la semana que viene"
  "la proxima semana"
  (datetime 2013 2 18 :grain :week)

  "el pasado mes"
  (datetime 2013 1)

  "el mes que viene"
  "el proximo mes"
  (datetime 2013 3)

  "el año pasado"
  (datetime 2012)

  "este ano"
  (datetime 2013)

  "el año que viene"
  "el proximo ano"
  (datetime 2014)

  ; "a las tres y cuarto mañana por la tarde" ;ALEX
  ; (datetime 2013 2 13 15 15 :hour 15 :minute 15)

  ;"viernes a las doce"
  ;(datetime 2013 2 15 12 :hour 12 :day-of-week 5)

  ;"viernes a las 12:00 horas"
  ;(datetime 2013 2 15 12 0 :hour 12 :day-of-week 5 :minute 0)

  ;; Involving periods  ; look for grain-after-shift

  "en un segundo"
  (datetime 2013 2 12 4 30 1)

  "en un minuto"
  "en 1 min"
  (datetime 2013 2 12 4 31 0)

  "en 2 minutos"
  "en dos minutos"
  (datetime 2013 2 12 4 32 0)

  "en 60 minutos"
  (datetime 2013 2 12 5 30 0)

  "en una hora"
  (datetime 2013 2 12 5 30)

  "hace dos horas"
  (datetime 2013 2 12 2 30)

  "en 24 horas"
  "en veinticuatro horas"
  (datetime 2013 2 13 4 30)

  "en un dia"
  (datetime 2013 2 13 4)

  "en 7 dias"
  (datetime 2013 2 19 4)

  "en una semana"
  (datetime 2013 2 19)

  "hace tres semanas"
  (datetime 2013 1 22)

  "en dos meses"
  (datetime 2013 4 12)

  "hace tres meses"
  (datetime 2012 11 12)

  "en un ano"
  "en 1 año"
  (datetime 2014 2)

  "hace dos años"
  (datetime 2011 2)

 ; Intervals involving cycles

  "pasados 2 segundos"
  (datetime-interval [2013 2 12 4 29 58] [2013 2 12 4 30 00])

  "proximos 3 segundos"
  (datetime-interval [2013 2 12 4 30 01] [2013 2 12 4 30 04])

  "pasados 2 minutos"
  (datetime-interval [2013 2 12 4 28] [2013 2 12 4 30])

  "proximos 3 minutos"
  (datetime-interval [2013 2 12 4 31] [2013 2 12 4 34])

  "proximas 3 horas"
  (datetime-interval [2013 2 12 5] [2013 2 12 8])

  "pasados 2 dias"
  (datetime-interval [2013 2 10] [2013 2 12])

  "proximos 3 dias"
  (datetime-interval [2013 2 13] [2013 2 16])

  "pasadas dos semanas"
  (datetime-interval [2013 1 28 :grain :week] [2013 2 11 :grain :week])

  "3 proximas semanas"
  "3 semanas que vienen"
  (datetime-interval [2013 2 18 :grain :week] [2013 3 11 :grain :week])

  "pasados 2 meses"
  "dos pasados meses"
  (datetime-interval [2012 12] [2013 02])

  "3 próximos meses"
  "proximos tres meses"
  "tres meses que vienen"
  (datetime-interval [2013 3] [2013 6])

  "pasados 2 anos"
  "dos pasados años"
  (datetime-interval [2011] [2013])

  "3 próximos años"
  "proximo tres años"
  "3 años que vienen"
  (datetime-interval [2014] [2017])


)
