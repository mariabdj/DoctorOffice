"use strict";

new Vue({
  el: "#app",
  data: {
    today: moment(),
    dateContext: moment(),
    selectedDate: moment(),
    days: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
    appointmentsByPatientAndDate: {},
    patientId: '' // Don't set patientId here

  },
  computed: {
    year: function year() {
      return this.dateContext.format("Y");
    },
    month: function month() {
      return this.dateContext.format("MMMM");
    },
    daysInMonth: function daysInMonth() {
      return this.dateContext.daysInMonth();
    },
    currentDate: function currentDate() {
      return this.dateContext.get("date");
    },
    firstDayOfMonth: function firstDayOfMonth() {
      var firstDay = moment(this.dateContext).startOf('month');
      return firstDay.weekday();
    },
    previousMonth: function previousMonth() {
      return moment(this.dateContext).subtract(1, "month");
    },
    previousMonthAsString: function previousMonthAsString() {
      return this.previousMonth.format("MMMM");
    },
    nextMonth: function nextMonth() {
      return moment(this.dateContext).add(1, "month");
    },
    nextMonthAsString: function nextMonthAsString() {
      return this.nextMonth.format("MMMM");
    },
    daysInPreviousMonth: function daysInPreviousMonth() {
      return this.previousMonth.daysInMonth();
    },
    daysFromPreviousMonth: function daysFromPreviousMonth() {
      var daysList = [];
      var count = this.daysInPreviousMonth - this.firstDayOfMonth + 1;

      for (var i = count; i <= this.daysInPreviousMonth; i++) {
        daysList.push(i);
      }

      return daysList;
    },
    dateList: function dateList() {
      var _this = this;

      var dateList = [];
      var daysFromPreviousMonth = this.daysFromPreviousMonth;
      var firstDayOfMonth = this.firstDayOfMonth;
      var daysInMonth = this.daysInMonth;
      daysFromPreviousMonth.forEach(function (day, index) {
        dateList.push({
          key: index,
          dayNumber: day,
          date: moment(_this.previousMonth).date(day).format('YYYY-MM-DD'),
          blank: true,
          today: false,
          additional: {
            month: _this.previousMonthAsString,
            year: _this.previousMonth.format('YYYY')
          },
          weekDay: _this.getWeekDay(day),
          moment: moment(_this.previousMonth).date(day)
        });
      });

      for (var i = 1; i <= daysInMonth; i++) {
        dateList.push({
          key: firstDayOfMonth + i - 1,
          dayNumber: i,
          date: moment(this.dateContext).date(i).format('YYYY-MM-DD'),
          blank: false,
          today: this.today.isSame(moment(this.dateContext).date(i), 'day'),
          additional: false,
          weekDay: this.getWeekDay(firstDayOfMonth + i - 1),
          moment: moment(this.dateContext).date(i)
        });
      }

      var remainingDays = (7 - dateList.length % 7) % 7;

      for (var _i = 1; _i <= remainingDays; _i++) {
        dateList.push({
          key: dateList.length,
          dayNumber: _i,
          date: moment(this.nextMonth).date(_i).format('YYYY-MM-DD'),
          blank: true,
          today: false,
          additional: {
            month: this.nextMonthAsString,
            year: this.nextMonth.format('YYYY')
          },
          weekDay: this.getWeekDay(dateList.length),
          moment: moment(this.nextMonth).date(_i)
        });
      }

      return dateList;
    },
    selectedDayAndMonth: function selectedDayAndMonth() {
      var dayAndMonth = this.selectedDate.format("D MMMM");
      dayAndMonth = dayAndMonth.split(" ");
      return {
        day: dayAndMonth[0],
        month: dayAndMonth[1]
      };
    },
    selectedWeekDay: function selectedWeekDay() {
      return this.selectedDate.format("dddd");
    },
    todayInCurrentMonthAndYear: function todayInCurrentMonthAndYear() {
      return this.month === this.today.format("MMMM") && this.year === this.today.format("Y");
    },
    todayIsEqualSelectDate: function todayIsEqualSelectDate() {
      return this.selectedDate.isSame(this.today, 'day');
    }
  },
  methods: {
    fetchPatientId: function fetchPatientId() {
      var response;
      return regeneratorRuntime.async(function fetchPatientId$(_context) {
        while (1) {
          switch (_context.prev = _context.next) {
            case 0:
              _context.prev = 0;
              _context.next = 3;
              return regeneratorRuntime.awrap(axios.get('/getPatientIdFromSession'));

            case 3:
              response = _context.sent;
              this.patientId = response.data;
              console.log("Patient ID:", this.patientId);
              this.fetchAppointments();
              _context.next = 12;
              break;

            case 9:
              _context.prev = 9;
              _context.t0 = _context["catch"](0);
              console.error("Error fetching patient ID:", _context.t0);

            case 12:
            case "end":
              return _context.stop();
          }
        }
      }, null, this, [[0, 9]]);
    },
    fetchAppointments: function fetchAppointments() {
      var year, month, response;
      return regeneratorRuntime.async(function fetchAppointments$(_context2) {
        while (1) {
          switch (_context2.prev = _context2.next) {
            case 0:
              console.log("Fetching appointments...");
              year = this.dateContext.year();
              month = this.dateContext.month() + 1; // month() is zero-indexed

              console.log("Patient ID:", this.patientId); // Logging patientId before the request

              _context2.prev = 4;
              _context2.next = 7;
              return regeneratorRuntime.awrap(axios.get('/getAppointmentsByMonth', {
                params: {
                  patientId: this.patientId,
                  // Ensure patientId is passed as a parameter
                  year: year,
                  month: month
                }
              }));

            case 7:
              response = _context2.sent;
              console.log("Axios request URL:", response.config.url); // Logging Axios request URL

              console.log("Axios request params:", response.config.params); // Logging Axios request parameters

              this.appointmentsByPatientAndDate = response.data;
              _context2.next = 16;
              break;

            case 13:
              _context2.prev = 13;
              _context2.t0 = _context2["catch"](4);
              console.error("Error fetching appointments:", _context2.t0);

            case 16:
            case "end":
              return _context2.stop();
          }
        }
      }, null, this, [[4, 13]]);
    },
    addMonth: function addMonth() {
      this.dateContext = moment(this.dateContext).add(1, 'month');
      this.fetchAppointments();
    },
    subtractMonth: function subtractMonth() {
      this.dateContext = moment(this.dateContext).subtract(1, 'month');
      this.fetchAppointments();
    },
    setSelectedDate: function setSelectedDate(moment) {
      this.selectedDate = moment;
    },
    goToday: function goToday() {
      this.selectedDate = this.today;
      this.dateContext = this.today;
      this.fetchAppointments();
    },
    formattingDay: function formattingDay(day) {
      return ("0" + day).slice(-2);
    },
    getWeekDay: function getWeekDay(day) {
      var index = day % 7;
      return this.days[index === 0 ? 6 : index - 1];
    },
    formatAppointmentTime: function formatAppointmentTime(time) {
      return moment(time, 'HH:mm').format('h:mm A');
    },
    getAppointmentsForDate: function getAppointmentsForDate(date) {
      var formattedDate = date.format('YYYY-MM-DD');
      return this.appointmentsByPatientAndDate[formattedDate] || [];
    }
  },
  created: function created() {
    // Fetch the patient ID from the session
    this.fetchPatientId();
  },
  filters: {
    capitalize: function capitalize(value) {
      if (!value) return "";
      value = value.toString();
      return value.charAt(0).toUpperCase() + value.slice(1);
    }
  }
});