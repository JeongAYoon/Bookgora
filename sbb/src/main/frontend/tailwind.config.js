/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    extend: {
      dropShadow: {
        'md': '0 4px 3px rgba(0, 0, 0, 0.5)'
      }
    },
  },
  plugins: [],
}

