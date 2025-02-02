import express from 'express';
import cors from 'cors';
import puppeteer from 'puppeteer';

const app = express();
const PORT = process.env.PORT || 3000;

app.use(cors());
app.use(express.json());

// Fetch cars from Mekina.net
async function fetchCarsFromMekina(searchQuery) {
    const browser = await puppeteer.launch();
    const page = await browser.newPage();
    const url = `https://www.mekina.net/cars/search?q=${encodeURIComponent(searchQuery)}`;

    await page.goto(url, { waitUntil: 'networkidle2' });

    const cars = await page.evaluate(() => {
        const carElements = document.querySelectorAll('.relative .m-1 a');
        return Array.from(carElements).map(element => {
            const title = element.querySelector('.text-sm')?.innerText || '';
            const price = element.querySelector('.flex.flex-col.justify-between.bg-primary-main')?.innerText || '';
            const link = element.href || '';
            const image = element.querySelector('img')?.src || '';
            const location = ''; // Adjust if location is available in the HTML structure

            return { title, price, link, location, image };
        });
    });

    await browser.close();
    return cars;
}

// Fetch cars from Jiji (You need to implement scraping logic for Jiji here)
async function fetchCarsFromJiji(searchQuery) {
    // Similar scraping logic to Mekina should go here
    return []; // Replace with actual scraping results from Jiji
}

app.get('/api/cars', async (req, res) => {
    const { query, platform } = req.query;

    if (!query) {
        return res.status(400).json({ error: 'Query parameter is required' });
    }

    try {
        let cars = [];

        if (platform === 'mekina' || platform === 'both') {
            const mekinaCars = await fetchCarsFromMekina(query);
            cars = [...cars, ...mekinaCars];
        }

        if (platform === 'jiji' || platform === 'both') {
            const jijiCars = await fetchCarsFromJiji(query);
            cars = [...cars, ...jijiCars];
        }

        res.json(cars);
    } catch (error) {
        console.error('Error fetching car listings:', error);
        res.status(500).json({ error: 'Failed to fetch car listings' });
    }
});

app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});
