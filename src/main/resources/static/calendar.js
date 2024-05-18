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
        year() {
            return this.dateContext.format("Y");
        },
        month() {
            return this.dateContext.format("MMMM");
        },
        daysInMonth() {
            return this.dateContext.daysInMonth();
        },
        currentDate() {
            return this.dateContext.get("date");
        },
        firstDayOfMonth() {
            let firstDay = moment(this.dateContext).startOf('month');
            return firstDay.weekday();
        },
        previousMonth() {
            return moment(this.dateContext).subtract(1, "month");
        },
        previousMonthAsString() {
            return this.previousMonth.format("MMMM");
        },
        nextMonth() {
            return moment(this.dateContext).add(1, "month");
        },
        nextMonthAsString() {
            return this.nextMonth.format("MMMM");
        },
        daysInPreviousMonth() {
            return this.previousMonth.daysInMonth();
        },
        daysFromPreviousMonth() {
            let daysList = [];
            let count = this.daysInPreviousMonth - this.firstDayOfMonth + 1;
            for (let i = count; i <= this.daysInPreviousMonth; i++) {
                daysList.push(i);
            }
            return daysList;
        },
        dateList() {
            let dateList = [];
            let daysFromPreviousMonth = this.daysFromPreviousMonth;
            let firstDayOfMonth = this.firstDayOfMonth;
            let daysInMonth = this.daysInMonth;

            daysFromPreviousMonth.forEach((day, index) => {
                dateList.push({
                    key: index,
                    dayNumber: day,
                    date: moment(this.previousMonth).date(day).format('YYYY-MM-DD'),
                    blank: true,
                    today: false,
                    additional: {
                        month: this.previousMonthAsString,
                        year: this.previousMonth.format('YYYY')
                    },
                    weekDay: this.getWeekDay(day),
                    moment: moment(this.previousMonth).date(day)
                });
            });

            for (let i = 1; i <= daysInMonth; i++) {
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

            let remainingDays = (7 - (dateList.length % 7)) % 7;
            for (let i = 1; i <= remainingDays; i++) {
                dateList.push({
                    key: dateList.length,
                    dayNumber: i,
                    date: moment(this.nextMonth).date(i).format('YYYY-MM-DD'),
                    blank: true,
                    today: false,
                    additional: {
                        month: this.nextMonthAsString,
                        year: this.nextMonth.format('YYYY')
                    },
                    weekDay: this.getWeekDay(dateList.length),
                    moment: moment(this.nextMonth).date(i)
                });
            }

            return dateList;
        },
        selectedDayAndMonth() {
            let dayAndMonth = this.selectedDate.format("D MMMM");
            dayAndMonth = dayAndMonth.split(" ");
            return {
                day: dayAndMonth[0],
                month: dayAndMonth[1]
            };
        },
        selectedWeekDay() {
            return this.selectedDate.format("dddd");
        },
        todayInCurrentMonthAndYear() {
            return this.month === this.today.format("MMMM") && this.year === this.today.format("Y");
        },
        todayIsEqualSelectDate() {
            return this.selectedDate.isSame(this.today, 'day');
        }
    },
    methods: {

        async fetchPatientId() {
            try {
                const response = await axios.get('/getPatientIdFromSession');
                this.patientId = response.data;
                console.log("Patient ID:", this.patientId);
                this.fetchAppointments();
            } catch (error) {
                console.error("Error fetching patient ID:", error);
            }
        },

        async fetchAppointments() {
            console.log("Fetching appointments...");
            const year = this.dateContext.year();
            const month = this.dateContext.month() + 1; // month() is zero-indexed
            console.log("Patient ID:", this.patientId); // Logging patientId before the request
            try {
                const response = await axios.get('/getAppointmentsByMonth', {
                    params: {
                        patientId: this.patientId, // Ensure patientId is passed as a parameter
                        year: year,
                        month: month
                    }
                });
                console.log("Axios request URL:", response.config.url); // Logging Axios request URL
                console.log("Axios request params:", response.config.params); // Logging Axios request parameters
                this.appointmentsByPatientAndDate = response.data;
            } catch (error) {
                console.error("Error fetching appointments:", error);
            }
        },
               
        
        addMonth() {
            this.dateContext = moment(this.dateContext).add(1, 'month');
            this.fetchAppointments();
        },
        subtractMonth() {
            this.dateContext = moment(this.dateContext).subtract(1, 'month');
            this.fetchAppointments();
        },
        setSelectedDate(moment) {
            this.selectedDate = moment;
        },
        goToday() {
            this.selectedDate = this.today;
            this.dateContext = this.today;
            this.fetchAppointments();
        },
        formattingDay(day) {
            return ("0" + day).slice(-2);
        },
        getWeekDay(day) {
            let index = day % 7;
            return this.days[index === 0 ? 6 : index - 1];
        },
        formatAppointmentTime(time) {
            return moment(time, 'HH:mm').format('h:mm A');
        },
        getAppointmentsForDate(date) {
            const formattedDate = date.format('YYYY-MM-DD');
            return this.appointmentsByPatientAndDate[formattedDate] || [];
        }
    },
    created() {
        // Fetch the patient ID from the session
        this.fetchPatientId();
    },   
          
    filters: {
        capitalize(value) {
            if (!value) return "";
            value = value.toString();
            return value.charAt(0).toUpperCase() + value.slice(1);
        }
    }
});
