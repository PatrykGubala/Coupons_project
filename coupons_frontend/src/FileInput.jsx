import React, { useState } from 'react';
import axios from 'axios';

function FileInput({ onUploadComplete }) {
    const [selectedFile, setSelectedFile] = useState(null);

    const handleFileChange = (event) => {
        setSelectedFile(event.target.files[0]);
    };

    const handleUpload = async () => {
        if (selectedFile) {
            const formData = new FormData();
            formData.append('file', selectedFile);

            try {
                await axios.post('http://localhost:8080/coupons/upload-excel', formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                })
                    .then(response => {
                        console.log('Upload successful', response.data);
                        onUploadComplete();
                    })
                    .catch(error => {
                        console.error('Error uploading file:', error);
                    });

            } catch (error) {
                console.error('Error uploading file:', error);
            }
        } else {
            alert('Please select a file to upload.');
        }
    };

    return (
        <div>
            <input type="file" onChange={handleFileChange} />
            <button onClick={handleUpload} disabled={!selectedFile}>Send</button>
            {selectedFile && <p>File selected: {selectedFile.name}</p>}
        </div>
    );
}

export default FileInput;
