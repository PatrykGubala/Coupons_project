import React, { useState } from 'react';
import { Card, CardContent, Typography, Button, CardActions, Collapse } from '@mui/material';

function CouponWidget({ coupon }) {
    const [expanded, setExpanded] = useState(false);
    const formatDate = (dateString) => {
        const date = new Date(dateString);
        return date.toISOString().split('T')[0];
    };
    const handleUseCodeClick = () => {
        let url = coupon.destinationUrl;
        if (url && url.trim() !== '') {
            if (!url.startsWith('http://') && !url.startsWith('https://')) {
                url = 'https://' + url;
            }
            window.open(url, '_blank', 'noopener noreferrer');
        } else {
            alert('Link do kodu rabatowego jest niedostępny.');
        }
    };

    return (
        <Card className="coupon-widget" sx={{ width: 1200, m: 'auto', mb: 2 }}>
            <CardContent>
                <Typography gutterBottom variant="h5" component="div">
                    {coupon.title}
                </Typography>
                {coupon.content && (
                    <Collapse in={expanded} timeout="auto" unmountOnExit>
                        <Typography variant="body2" color="text.secondary">
                            {coupon.content}
                        </Typography>
                    </Collapse>
                )}
                <Typography variant="body1" color="primary">
                    Zniżka: <strong>{coupon.discountValue} PLN</strong>
                </Typography>
                <Typography variant="body1" style={{ marginTop: '10px' }}>
                    Kod: <strong>{coupon.code}</strong>
                </Typography>
                <Typography variant="body2" color="text.secondary">
                    Data rozpoczęcia: {coupon.date ? formatDate(coupon.date) : 'N/A'}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                    Data ważności: {coupon.expires ? formatDate(coupon.expires) : 'N/A'}
                </Typography>

            </CardContent>
            <CardActions style={{ justifyContent: 'space-between', padding: '0 16px 8px' }}>
                <Button size="small" onClick={() => setExpanded(!expanded)}>
                    {expanded ? 'Zwiń' : 'Rozwiń'}
                </Button>
                <Button size="small" variant="contained" color="primary" onClick={handleUseCodeClick}>
                    Użyj kodu
                </Button>
            </CardActions>
        </Card>
    );
}

export default CouponWidget;
