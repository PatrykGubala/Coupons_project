import React from 'react';
import FileInput from './FileInput';
import CouponList from './CouponList';

function App() {
    const handleUploadComplete = () => {
        window.location.reload();
    };

    return (
        <div className="App">
            <h1>Wstaw Plik xlsx excel</h1>
            <FileInput onUploadComplete={handleUploadComplete} />
            <h2>Coupons</h2>
            <CouponList />
        </div>
    );
}

export default App;
