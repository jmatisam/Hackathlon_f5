// app.js

const API_BASE_URL = 'http://localhost:8080/api'; // Asegúrate de que esta URL sea correcta

function login() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    fetch(`${API_BASE_URL}/auth`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password })
    })
    .then(response => {
        if (response.ok) {
            return response.text();
        } else {
            throw new Error('Invalid credentials');
        }
    })
    .then(text => {
        if (text === 'Authentication successful') {
            localStorage.setItem('loggedIn', 'true');
            window.location.href = 'index.html';
        } else {
            alert('Invalid credentials');
        }
    })
    .catch(error => console.error('Error:', error));
}

function logout() {
    localStorage.removeItem('loggedIn');
    window.location.href = 'login.html';
}

function addToFavorites() {
    const country = document.getElementById('countrySelect').value;
    const city = document.getElementById('citySelect').value;
    
    // Lógica para agregar a favoritos
    alert(`Added ${city}, ${country} to favorites`);
}

function checkLogin() {
    if (!localStorage.getItem('loggedIn')) {
        window.location.href = 'login.html';
    }
}

document.addEventListener('DOMContentLoaded', (event) => {
    if (window.location.pathname.includes('index.html')) {
        checkLogin();
        loadAirQualityData();
    }
});

function loadAirQualityData() {
    const city = document.getElementById('citySelect').value;
    if (city) {
        fetch(`${API_BASE_URL}/airquality/${city}`)
            .then(response => response.json())
            .then(data => {
                document.getElementById('selectedCity').textContent = city;
                const airQualityData = document.getElementById('airQualityData');
                airQualityData.innerHTML = `<p>AQI: ${data.aqi}</p><p>Status: ${data.status}</p>`;
            })
            .catch(error => console.error('Error:', error));
    }
}
document.addEventListener("DOMContentLoaded", function () {
    const citySelect = document.getElementById("citySelect");
    const airQualityData = document.getElementById("airQualityData");
    const selectedCity = document.getElementById("selectedCity");

    window.getAirQuality = function () {
        const city = citySelect.value;
        if (city) {
            fetch(`http://localhost:8080/api/airquality/${city}`)
                .then(response => response.json())
                .then(data => {
                    selectedCity.textContent = city.charAt(0).toUpperCase() + city.slice(1);
                    displayAirQualityData(data);
                })
                .catch(error => console.error('Error:', error));
        }
    }

    function displayAirQualityData(data) {
        airQualityData.innerHTML = '';

        const parameters = {
            AQI: data.values.aqi,
            PM25: data.values.pm25,
            PM10: data.values.pm10,
            NO2: data.values.no2,
            O3: data.values.o3,
            SO2: data.values.so2,
            CO: data.values.co,
            Temperature: data.values.t,
            Humidity: data.values.h,
            Pressure: data.values.p,
            WindSpeed: data.values.wg
        };

        for (const [key, value] of Object.entries(parameters)) {
            const col = document.createElement('div');
            col.className = 'col-md-3';
            col.innerHTML = `
                <div class="card mb-3">
                    <div class="card-body">
                        <h5 class="card-title">${key}</h5>
                        <p class="card-text">${value}</p>
                    </div>
                </div>
            `;
            airQualityData.appendChild(col);
        }
    }
});